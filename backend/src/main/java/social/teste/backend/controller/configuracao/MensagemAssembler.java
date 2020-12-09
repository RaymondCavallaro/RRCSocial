package social.teste.backend.controller.configuracao;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static social.teste.backend.controller.configuracao.EntityWrapper.of;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import social.teste.backend.controller.MensagemController;
import social.teste.backend.entidade.EntidadeMensagem;

public class MensagemAssembler {

	public static EntityWrapper<EntidadeMensagem> toResponse(EntidadeMensagem mensagem) {
		return of(mensagem, linkTo(methodOn(MensagemController.class).get(mensagem.getId())).withSelfRel(),
				linkTo(methodOn(MensagemController.class).findPaginated(null, null)).withRel("mensagem"));
	}

	public static CollectionModel<EntityWrapper<EntidadeMensagem>> toResponse(Page<EntidadeMensagem> page,
			int currentPage) {
		List<EntityWrapper<EntidadeMensagem>> lista = page.getContent().stream().map(MensagemAssembler::toResponse)
				.collect(Collectors.toList());
		List<Link> links = new ArrayList<>();
		links.add(linkTo(methodOn(MensagemController.class).findPaginated(currentPage, page.getSize())).withSelfRel());
		links.add(linkTo(methodOn(MensagemController.class).findPaginated(1, page.getSize()))
				.withRel(IanaLinkRelations.FIRST));
		if (currentPage != 1) {
			links.add(linkTo(methodOn(MensagemController.class).findPaginated(currentPage - 1, page.getSize()))
					.withRel(IanaLinkRelations.PREVIOUS));
		} else if (currentPage != page.getTotalPages()) {
			links.add(linkTo(methodOn(MensagemController.class).findPaginated(currentPage + 1, page.getSize()))
					.withRel(IanaLinkRelations.NEXT));
		}
		links.add(linkTo(methodOn(MensagemController.class).findPaginated(page.getTotalPages(), page.getSize()))
				.withRel(IanaLinkRelations.LAST));
		return CollectionModel.of(lista, links);
	}
}