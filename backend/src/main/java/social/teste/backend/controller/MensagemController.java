package social.teste.backend.controller;

import static social.teste.backend.controller.configuracao.MensagemAssembler.toResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import social.teste.backend.controller.configuracao.EntityWrapper;
import social.teste.backend.controller.configuracao.MensagemNotFoundException;
import social.teste.backend.entidade.EntidadeMensagem;
import social.teste.backend.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

	@Autowired
	private MensagemService mensagemService;

	@RequestMapping(method = { RequestMethod.GET }, produces = "application/json")
	public CollectionModel<EntityWrapper<EntidadeMensagem>> findPaginated(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		Page<EntidadeMensagem> resultPage = mensagemService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new MensagemNotFoundException();
		}
		return toResponse(resultPage, page);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET }, produces = "application/json")
	public EntityWrapper<EntidadeMensagem> get(@PathVariable String id) {
		return toResponse(mensagemService.findById(id));
	}

	@RequestMapping(method = { RequestMethod.POST })
	ResponseEntity<EntityWrapper<EntidadeMensagem>> create(@RequestBody EntidadeMensagem mensagem) {
		EntityWrapper<EntidadeMensagem> entityModel = toResponse(mensagemService.save(mensagem));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
}