package rrc.social.server.resource.repository;

import static org.springframework.data.domain.PageRequest.of;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import rrc.social.server.resource.entidade.EntidadeMensagem;

@Component
public class MensagemRepository {

	public Page<EntidadeMensagem> findPaginated(Integer page, Integer size) {
		int total = 100;
		List<EntidadeMensagem> retorno = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			EntidadeMensagem mensagem = new EntidadeMensagem();
			mensagem.setId(Integer.toString(i));
			mensagem.setTitulo(Integer.toString(i));
			mensagem.setConteudo(Integer.toString(i + 1));
			retorno.add(mensagem);
		}
		return new PageImpl<>(retorno, of(page, size), total);
	}

	public EntidadeMensagem findById(String id) {
		EntidadeMensagem mensagem = new EntidadeMensagem();
		return mensagem;
	}

	public EntidadeMensagem save(EntidadeMensagem mensagem) {
		return mensagem;
	}
}