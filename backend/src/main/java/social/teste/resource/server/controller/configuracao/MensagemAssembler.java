package social.teste.resource.server.controller.configuracao;

import static org.springframework.hateoas.CollectionModel.of;
import static org.springframework.hateoas.EntityModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.hateoas.server.core.EmbeddedWrappers;

import social.teste.resource.server.controller.MensagemController;
import social.teste.resource.server.entidade.EntidadeMensagem;

public class MensagemAssembler {

	private static EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
	private static LinkRelation listaMensagem = LinkRelation.of("mensagem");

	public static EntityModel<EntidadeMensagem> toResponse(EntidadeMensagem mensagem) {
		return of(mensagem, linkTo(methodOn(MensagemController.class).get(mensagem.getId())).withSelfRel(),
				linkTo(methodOn(MensagemController.class).findPaginated(null, null)).withRel("mensagem"));
	}

	public static EmbeddedWrapper wrap(EntidadeMensagem mensagem) {
		return wrappers.wrap(toResponse(mensagem), listaMensagem);
	}

	public static CollectionModel<EmbeddedWrapper> toResponse(Page<EntidadeMensagem> page,
			int currentPage) {
		List<EmbeddedWrapper> lista = page.getContent().stream().map(MensagemAssembler::wrap)
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
		return of(lista, links);
	}
}