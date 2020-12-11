package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oauth_refresh_token")
public class OauthRefreshToken implements Serializable {

	private static final long serialVersionUID = -6189500565838361102L;

	String _id;
	@Field(value = "token_id")
	String tokenId;
	Binary token;
	Binary authentication;

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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

}