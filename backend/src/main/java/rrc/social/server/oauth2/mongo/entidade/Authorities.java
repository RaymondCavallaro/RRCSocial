package rrc.social.server.oauth2.mongo.entidade;

import java.io.Serializable;

public class Authorities implements Serializable {

	private static final long serialVersionUID = -6035754714847209210L;

	String _id;
	String username;
	String authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}