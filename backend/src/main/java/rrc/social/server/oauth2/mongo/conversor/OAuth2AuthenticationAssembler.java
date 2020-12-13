package rrc.social.server.oauth2.mongo.conversor;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
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

import rrc.social.server.oauth2.mongo.entidade.OauthAccessToken;
import rrc.social.server.oauth2.mongo.entidade.OauthCode;
import rrc.social.server.oauth2.mongo.entidade.Users;

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
		newToken.setAuthentication(om.writeValueAsString(authentication));
		newToken.setRefreshToken(token.getRefreshToken().getValue());

		return newToken;
	}

	public OauthCode assembleOauthCode(OAuth2Authentication authentication, Users user)
			throws JsonMappingException, JsonProcessingException {
		String code = UUID.randomUUID().toString();

		Map<String, Object> map = new HashMap<>();
		map.put("authenticationCode", code);
		map.put("user", user);
		authentication.setDetails(map);

		OauthCode auth = new OauthCode();
		auth.setCode(code);
		auth.setAuthentication(om.writeValueAsString(authentication));

		return auth;
	}

	public OAuth2Authentication assembleOAuth2Authentication(String authenticationString)
			throws JsonMappingException, JsonProcessingException {
		Map<String, Object> oldAu = om.readValue(authenticationString, Map.class);
		Map<String, Object> detailsMap = (Map<String, Object>) oldAu.get("details");
		Map<String, Object> userMap = (Map<String, Object>) detailsMap.get("user");
		Users user = om.readValue(om.writeValueAsString(userMap), Users.class);
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
				return userDetailsAssembler.assembleUserDetailsFromGrantedAuthority(user,
						grantedAuthorityAssembler.assembleGrantedAuthorities(
								(Collection<Map<String, String>>) ((Map<String, Object>) oldUa.get("principal"))
										.get("authorities")));
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
		OAuth2Authentication authentication = new OAuth2Authentication(oauth2Request, userAuthentication);
		detailsMap.put("user", user);
		authentication.setDetails(detailsMap);
		return authentication;
	}

	public OAuth2Authentication assembleOAuth2Authentication(OauthCode auth)
			throws JsonMappingException, JsonProcessingException {
		return assembleOAuth2Authentication(auth.getAuthentication());
	}

	public String assembleAuthenticationCode(OAuth2Authentication authentication) {
		Map<String, Object> map = (Map<String, Object>) authentication.getDetails();
		if (map != null) {
			return (String) map.get("authenticationCode");
		}
		return null;
	}
}