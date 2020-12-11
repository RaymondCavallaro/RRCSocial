package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oauth_access_token")
public class OauthAccessToken implements Serializable {

	private static final long serialVersionUID = 7901185089849826545L;

	String _id;
	@Field(value = "token_id")
	String tokenId;
	Binary token;
	@Field(value = "authentication_id")
	String authenticationId;
	@Field(value = "user_name")
	String userName;
	@Field(value = "client_id")
	String clientId;
	Binary authentication;
	@Field(value = "refresh_token")
	String refreshToken;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Binary getToken() {
		return token;
	}

	public void setToken(Binary token) {
		this.token = token;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Binary getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Binary authentication) {
		this.authentication = authentication;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}