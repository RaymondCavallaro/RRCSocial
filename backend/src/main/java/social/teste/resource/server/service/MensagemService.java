package social.teste.resource.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import social.teste.resource.server.entidade.EntidadeMensagem;
import social.teste.resource.server.repository.MensagemRepository;

@Component
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;

	public Page<EntidadeMensagem> findPaginated(Integer page, Integer size) {
		return mensagemRepository.findPaginated(page, size);
	}

	public EntidadeMensagem findById(String id) {
		return mensagemRepository.findById(id);
	}

	public EntidadeMensagem save(EntidadeMensagem mensagem) {
		return mensagemRepository.save(mensagem);
	}
}