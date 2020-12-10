package social.teste.oauth2.server.mongo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import social.teste.oauth2.server.mongo.conversor.OAuth2AccessTokenAssembler;
import social.teste.oauth2.server.mongo.entidade.OauthAccessToken;
import social.teste.oauth2.server.mongo.repository.OauthAccessTokenRepository;

@Component
public class MongoTokenStore implements TokenStore {

	@Autowired
	private OauthAccessTokenRepository oauthAccessTokenRepository;

	@Autowired
	private OAuth2AccessTokenAssembler oauth2AccessTokenAssembler;

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return null;
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		return null;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OauthAccessToken accessToken = oauthAccessTokenRepository.findByToken(tokenValue.getBytes());
		return oauth2AccessTokenAssembler.assembleOAuth2AccessToken(accessToken);
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		oauthAccessTokenRepository.deleteByToken(token.getValue().getBytes());
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		return null;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		return null;
	}

}