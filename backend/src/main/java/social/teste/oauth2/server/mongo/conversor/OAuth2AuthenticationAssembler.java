package social.teste.oauth2.server.mongo.conversor;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import social.teste.oauth2.server.mongo.entidade.OauthAccessToken;
import social.teste.oauth2.server.mongo.entidade.OauthCode;

@Component
public class OAuth2AuthenticationAssembler {

	@Autowired
	private ObjectMapper om;

	@Autowired
	private GrantedAuthorityAssembler grantedAuthorityAssembler;
	@Autowired
	private UserDetailsAssembler userDetailsAssembler;

	public OauthAccessToken assembleOauthAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication)
			throws JsonProcessingException {
		OauthAccessToken newToken = new OauthAccessToken();
		newToken.setToken(om.writeValueAsString(token));
		newToken.setTokenId(token.getValue());
		newToken.setAuthentication(assembleAuthenticationString(authentication));
		newToken.setRefreshToken(token.getRefreshToken().getValue());

		return newToken;
	}

	public OauthCode assembleOauthCode(OAuth2Authentication authentication)
			throws JsonMappingException, JsonProcessingException {
		OauthCode auth = new OauthCode();
		auth.setCode(UUID.randomUUID().toString());
		auth.setAuthentication(assembleAuthenticationString(authentication));

		authentication.setDetails(auth.getCode());

		return auth;
	}

	public String assembleAuthenticationString(OAuth2Authentication authentication)
			throws JsonMappingException, JsonProcessingException {
		return om.writeValueAsString(authentication);
	}

	public OAuth2Authentication assembleOAuth2Authentication(String authenticationString)
			throws JsonMappingException, JsonProcessingException {
		Map<String, Object> oldAu = om.readValue(authenticationString, Map.class);
		Map<String, Object> requestMap = (Map<String, Object>) oldAu.get("oauth2Request");
		OAuth2Request oauth2Request = new OAuth2Request((Map<String, String>) requestMap.get("requestParameters"),
				(String) requestMap.get("clientId"),
				grantedAuthorityAssembler
						.assembleGrantedAuthorities((Collection<Map<String, String>>) requestMap.get("authorities")),
				(boolean) requestMap.get("approved"), new HashSet<>((Collection<String>) requestMap.get("scope")),
				new HashSet<>((Collection<String>) requestMap.get("resourceIds")),
				(String) requestMap.get("redirectUri"),
				new HashSet<>((Collection<String>) requestMap.get("responseTypes")),
				(Map<String, Serializable>) requestMap.get("extensions"));
		Map<String, Object> oldUa = (Map<String, Object>) oldAu.get("userAuthentication");
		Authentication userAuthentication = new Authentication() {

			boolean isAuthenticated = (boolean) oldUa.get("authenticated");

			@Override
			public String getName() {
				return (String) oldUa.get("name");
			}

			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				this.isAuthenticated = isAuthenticated;
			}

			@Override
			public boolean isAuthenticated() {
				return isAuthenticated;
			}

			@Override
			public Object getPrincipal() {
				return userDetailsAssembler.assembleUserDetails((Map<String, Object>) oldUa.get("principal"));
			}

			@Override
			public Object getDetails() {
				return oldUa.get("details");
			}

			@Override
			public Object getCredentials() {
				return oldUa.get("credentials");
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return grantedAuthorityAssembler
						.assembleGrantedAuthorities((Collection<Map<String, String>>) oldUa.get("authorities"));
			}
		};
		return new OAuth2Authentication(oauth2Request, userAuthentication);
	}

	public OAuth2Authentication assembleOAuth2Authentication(OauthCode auth)
			throws JsonMappingException, JsonProcessingException {
		return assembleOAuth2Authentication(auth.getAuthentication());
	}

	public String assembleAuthenticationCode(OAuth2Authentication authentication) {
		return (String) authentication.getDetails();
	}
}