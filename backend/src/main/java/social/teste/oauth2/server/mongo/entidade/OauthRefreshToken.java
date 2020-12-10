package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;

public class OauthRefreshToken implements Serializable {

	private static final long serialVersionUID = -6189500565838361102L;

	String _id;
	@BsonProperty(value = "token_id")
	String token_id;
	Binary token;
	Binary authentication;

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public Binary getToken() {
		return token;
	}

	public void setToken(Binary token) {
		this.token = token;
	}

	public Binary getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Binary authentication) {
		this.authentication = authentication;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}