package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;

public class OauthClientToken implements Serializable {

	private static final long serialVersionUID = -7830216447254504559L;

	String _id;
	@BsonProperty(value = "token_id")
	String tokenId;
	@BsonProperty(value = "authentication_id")
	String authenticationId;
	@BsonProperty(value = "user_name")
	String userName;
	@BsonProperty(value = "client_id")
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