package rrc.social.auth.mongo.conversor;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rrc.social.auth.mongo.entidade.OauthAccessToken;
import rrc.social.auth.mongo.entidade.OauthRefreshToken;

@Component
public class OAuth2AccessTokenAssembler {

	@Autowired
	private ObjectMapper om;

	@Autowired
	private OAuth2RefreshTokenAssembler oauth2RefreshTokenAssembler;

	@Autowired
	private OAuth2AuthenticationAssembler oAuth2AuthenticationAssembler;

	public OAuth2AccessToken assembleOAuth2AccessToken(OauthAccessToken accessToken, OauthRefreshToken refreshToken)
			throws JsonMappingException, JsonProcessingException {
		Map<String, Object> oldAt = om.readValue(accessToken.getToken(), Map.class);
		return new OAuth2AccessToken() {

			@Override
			public Map<String, Object> getAdditionalInformation() {
				return oldAt;
			}

			@Override
			public Set<String> getScope() {
				String oldScope = (String) oldAt.get("scope");
				return new HashSet<>(Arrays.asList(oldScope.split(" ")));
			}

			@Override
			public OAuth2RefreshToken getRefreshToken() {
				return oauth2RefreshTokenAssembler.assembleOAuth2RefreshToken(refreshToken);
			}

			@Override
			public String getTokenType() {
				return (String) oldAt.get("tokenType");
			}

			@Override
			public boolean isExpired() {
				return false;
			}

			@Override
			public Date getExpiration() {
				return null;
			}

			@Override
			public int getExpiresIn() {
				return 0;
			}

			@Override
			public String getValue() {
				return new String(accessToken.getTokenId());
			}
		};
	}

	public OauthAccessToken assembleOauthAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication)
			throws JsonProcessingException {
		OauthAccessToken newToken = new OauthAccessToken();
		newToken.setToken(om.writeValueAsString(token));
		newToken.setTokenId(token.getValue());
		newToken.setAuthentication(oAuth2AuthenticationAssembler.assembleAuthenticationCode(authentication));
		newToken.setRefreshToken(token.getRefreshToken().getValue());

		return newToken;
	}

	public OAuth2Authentication assembleOAuth2Authentication(OauthAccessToken accessToken)
			throws JsonMappingException, JsonProcessingException {
		return oAuth2AuthenticationAssembler.assembleOAuth2Authentication(accessToken.getAuthentication());
	}
}