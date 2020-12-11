package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oauth_client_token")
public class OauthClientToken implements Serializable {

	private static final long serialVersionUID = -7830216447254504559L;

	String _id;
	@Field("token_id")
	String tokenId;
	@Field("authentication_id")
	String authenticationId;
	@Field("user_name")
	String userName;
	@Field("client_id")
	String clientId;
	Binary token;

	public Binary getToken() {
		return token;
	}

	public void setToken(Binary token) {
		this.token = token;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}