package rrc.social.server.resource.controller;

import static rrc.social.server.resource.controller.configuracao.MensagemAssembler.toResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rrc.social.server.resource.controller.configuracao.MensagemNotFoundException;
import rrc.social.server.resource.entidade.EntidadeMensagem;
import rrc.social.server.resource.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

	@Autowired
	private MensagemService mensagemService;

	@RequestMapping(method = { RequestMethod.GET }, produces = "application/json")
	public CollectionModel<EmbeddedWrapper> findPaginated(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		Page<EntidadeMensagem> resultPage = mensagemService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new MensagemNotFoundException();
		}
		return toResponse(resultPage, page);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET }, produces = "application/json")
	public EntityModel<EntidadeMensagem> get(@PathVariable String id) {
		return toResponse(mensagemService.findById(id));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = { RequestMethod.POST })
	EntityModel<EntidadeMensagem> create(@RequestBody EntidadeMensagem mensagem) {
		return toResponse(mensagemService.save(mensagem));
	}
}