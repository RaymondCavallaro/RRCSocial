package social.teste.backend.entidade;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import social.teste.backend.comportamento.InterfaceEntidade;

public class EntidadeMensagem implements InterfaceEntidade {

	@JsonProperty("id")
	private String id;
	@JsonProperty("texto")
	private String texto;
	@JsonProperty("likes")
	private Integer likes;

	@JsonIgnore
	private Map<String, Object> ignore = new HashMap<String, Object>();

	@Override
	@JsonIgnore
	public String getChaveEntidade() {
		return getId();
	}

	@JsonAnySetter
	public void setIgnore(String name, Object value) {
		this.ignore.put(name, value);
	}

	@JsonIgnore
	public void setIgnore(Map<String, Object> ignore) {
		this.ignore = ignore;
	}

	@JsonAnyGetter
	public Map<String, Object> getIgnore() {
		return this.ignore;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("texto")
	public String getTexto() {
		return texto;
	}

	@JsonProperty("texto")
	public void setTexto(String texto) {
		this.texto = texto;
	}

	@JsonProperty("likes")
	public Integer getLikes() {
		return likes;
	}

	@JsonProperty("likes")
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
}