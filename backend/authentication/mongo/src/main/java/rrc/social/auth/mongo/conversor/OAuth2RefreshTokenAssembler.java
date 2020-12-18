package rrc.social.auth.mongo.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import rrc.social.auth.mongo.entidade.OauthRefreshToken;

@Component
public class OAuth2RefreshTokenAssembler {

	@Autowired
	private OAuth2AuthenticationAssembler oAuth2AuthenticationAssembler;

	public OAuth2RefreshToken assembleOAuth2RefreshToken(OauthRefreshToken refreshToken) {
		return new OAuth2RefreshToken() {

			@Override
			public String getValue() {
				return refreshToken.getTokenId();
			}
		};
	}

	public OauthRefreshToken assembleOauthRefreshToken(OAuth2RefreshToken refreshToken,
			OAuth2Authentication authentication) throws JsonMappingException, JsonProcessingException {
		OauthRefreshToken token = new OauthRefreshToken();
		token.setAuthentication(oAuth2AuthenticationAssembler.assembleAuthenticationCode(authentication));
		token.setTokenId(refreshToken.getValue());

		return token;
	}

	public OAuth2Authentication assembleOAuth2Authentication(OauthRefreshToken refreshToken)
			throws JsonMappingException, JsonProcessingException {
		return oAuth2AuthenticationAssembler.assembleOAuth2Authentication(refreshToken.getAuthentication());
	}
}