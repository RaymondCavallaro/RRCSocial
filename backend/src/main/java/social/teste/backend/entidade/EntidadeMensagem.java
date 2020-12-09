package social.teste.backend.entidade;

import java.io.Serializable;

import social.teste.backend.entidade.configuracao.EntidadeUtil;
import social.teste.backend.entidade.configuracao.Hashable;

public class EntidadeMensagem implements Hashable, Serializable {

	private static final long serialVersionUID = 3721103743945063043L;

	private String id;
	private String titulo;
	private String conteudo;
	private Integer upvotes;

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

	public Integer getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}

	public String geraHash() {
		return EntidadeUtil.geraHash(this.id, this.titulo, this.conteudo, this.upvotes);
	}
}