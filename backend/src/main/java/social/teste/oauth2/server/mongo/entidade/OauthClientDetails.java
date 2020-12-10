package social.teste.oauth2.server.mongo.entidade;

import java.io.Serializable;
import java.util.Set;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	String _id;
	@BsonProperty(value = "client_id")
	String clientId;
	@BsonProperty(value = "resource_ids")
	Set<String> resourceIds;
	@BsonProperty(value = "client_secret")
	String clientSecret;
	Set<String> scope;
	@BsonProperty(value = "authorized_grant_types")
	Set<String> authorizedGrantTypes;
	@BsonProperty(value = "web_server_redirect_uri")
	Set<String> webServerRedirectUri;
	Set<String> authorities;
	@BsonProperty(value = "access_token_validity")
	Integer accessTokenValidity;
	@BsonProperty(value = "refresh_token_validity")
	Integer refreshTokenValidity;
	@BsonProperty(value = "additional_information")
	String additionalInformation;
	String autoapprove;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Set<String> getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(Set<String> webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}