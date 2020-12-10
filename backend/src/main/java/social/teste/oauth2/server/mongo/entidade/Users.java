package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;

public class Users implements Serializable {

	private static final long serialVersionUID = 2884117363612428012L;

	String _id;
	String username;
	String password;
	boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}