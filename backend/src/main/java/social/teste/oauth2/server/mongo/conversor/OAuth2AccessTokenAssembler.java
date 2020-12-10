package social.teste.oauth2.server.mongo.conversor;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.stereotype.Component;

import social.teste.oauth2.server.mongo.entidade.OauthAccessToken;

@Component
public class OAuth2AccessTokenAssembler {

	@Autowired
	private OAuth2RefreshTokenAssembler oauth2RefreshTokenAssembler;

	public OAuth2AccessToken assembleOAuth2AccessToken(OauthAccessToken accessToken) {
		return new OAuth2AccessToken() {

			@Override
			public Map<String, Object> getAdditionalInformation() {
				return null;
			}

			@Override
			public Set<String> getScope() {
				return null;
			}

			@Override
			public OAuth2RefreshToken getRefreshToken() {
				return oauth2RefreshTokenAssembler.assembleOAuth2RefreshToken(accessToken.getRefreshToken());
			}

			@Override
			public String getTokenType() {
				return null;
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
				return new String(accessToken.getToken().getData());
			}
		};
	}

	public OauthAccessToken assembleOauthAccessToken(OAuth2AccessToken token) {
		return null;
	}

}