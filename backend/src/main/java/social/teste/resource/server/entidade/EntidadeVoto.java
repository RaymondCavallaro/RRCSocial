package social.teste.resource.server.entidade;

import java.io.Serializable;

import social.teste.resource.server.entidade.configuracao.EntidadeUtil;
import social.teste.resource.server.entidade.configuracao.Hashable;

public class EntidadeVoto implements Hashable, Serializable {

	private static final long serialVersionUID = -420972687085034009L;

	private String mensagem;
	private String votante;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getVotante() {
		return votante;
	}

	public void setVotante(String votante) {
		this.votante = votante;
	}

	public String geraHash() {
		return EntidadeUtil.geraHash(this.mensagem, this.votante);
	}
}