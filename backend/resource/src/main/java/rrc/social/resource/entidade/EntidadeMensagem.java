package rrc.social.resource.entidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeName;

import rrc.social.resource.entidade.configuracao.EntidadeUtil;
import rrc.social.resource.entidade.configuracao.Hashable;

@JsonTypeName("mensagem")
public class EntidadeMensagem implements Hashable, Serializable {

	private static final long serialVersionUID = 3721103743945063043L;

	private String id;
	private String titulo;
	private String conteudo;

	public String getChaveEntidade() {
		return getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String geraHash() {
		return EntidadeUtil.geraHash(this.id, this.titulo, this.conteudo);
	}
}