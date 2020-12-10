package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;

public class OauthAccessToken implements Serializable {

	private static final long serialVersionUID = 7901185089849826545L;

	String _id;
	@BsonProperty(value = "token_id")
	String tokenId;
	Binary token;
	@BsonProperty(value = "authentication_id")
	String authenticationId;
	@BsonProperty(value = "user_name")
	String userName;
	@BsonProperty(value = "client_id")
	String clientId;
	Binary authentication;
	@BsonProperty(value = "refresh_token")
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