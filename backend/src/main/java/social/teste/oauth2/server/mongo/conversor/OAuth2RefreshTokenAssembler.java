package social.teste.oauth2.server.mongo.conversor;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class OAuth2RefreshTokenAssembler {

	public OAuth2RefreshToken assembleOAuth2RefreshToken(String refreshToken) {
		return new OAuth2RefreshToken() {

			@Override
			public String getValue() {
				return refreshToken;
			}
		};
	}
}