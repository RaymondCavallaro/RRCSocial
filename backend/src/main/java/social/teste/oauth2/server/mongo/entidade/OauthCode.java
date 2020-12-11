package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "oauth_code")
public class OauthCode implements Serializable {

	private static final long serialVersionUID = -1765066648443647470L;

	String _id;
	String code;
	Binary authentication;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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