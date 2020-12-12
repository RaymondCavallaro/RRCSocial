package social.teste.oauth2.server.mongo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import social.teste.oauth2.server.mongo.conversor.OAuth2AccessTokenAssembler;
import social.teste.oauth2.server.mongo.conversor.OAuth2AuthenticationAssembler;
import social.teste.oauth2.server.mongo.conversor.OAuth2RefreshTokenAssembler;
import social.teste.oauth2.server.mongo.entidade.OauthAccessToken;
import social.teste.oauth2.server.mongo.entidade.OauthCode;
import social.teste.oauth2.server.mongo.entidade.OauthRefreshToken;
import social.teste.oauth2.server.mongo.repository.OauthAccessTokenRepository;
import social.teste.oauth2.server.mongo.repository.OauthCodeRepository;
import social.teste.oauth2.server.mongo.repository.OauthRefreshTokenRepository;

@Component
public class MongoTokenStore implements TokenStore {

	@Autowired
	private OauthAccessTokenRepository oauthAccessTokenRepository;
	@Autowired
	private OauthRefreshTokenRepository oauthRefreshTokenRepository;
	@Autowired
	private OauthCodeRepository oauthCodeRepository;

	@Autowired
	private OAuth2AuthenticationAssembler oauth2AuthenticationAssembler;
	@Autowired
	private OAuth2AccessTokenAssembler oauth2AccessTokenAssembler;
	@Autowired
	private OAuth2RefreshTokenAssembler oauth2RefreshTokenAssembler;

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		OauthAccessToken accessToken = oauthAccessTokenRepository.findByTokenId(token);
		if (accessToken == null) {
			return null;
		}
		try {
			return restoreAuthentication(accessToken.getAuthentication());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		try {
			storeAuthentication(authentication);
			OauthAccessToken accessToken = oauth2AccessTokenAssembler.assembleOauthAccessToken(token, authentication);
			oauthAccessTokenRepository.save(accessToken);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void storeAuthentication(OAuth2Authentication authentication) throws JsonMappingException, JsonProcessingException {
		String authenticationId = (String) authentication.getDetails();
		if (authenticationId == null) {
			OauthCode auth = oauth2AuthenticationAssembler.assembleOauthCode(authentication);
			oauthCodeRepository.save(auth);
		}
	}

	private OAuth2Authentication restoreAuthentication(String authentication) throws JsonMappingException, JsonProcessingException {
		OauthCode auth = oauthCodeRepository.findAllByCode(authentication);
		return oauth2AuthenticationAssembler.assembleOAuth2Authentication(auth);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OauthAccessToken accessToken = oauthAccessTokenRepository.findByTokenId(tokenValue);
		if (accessToken == null) {
			return null;
		}
		OauthRefreshToken refreshToken = oauthRefreshTokenRepository.findByTokenId(accessToken.getRefreshToken());
		try {
			return oauth2AccessTokenAssembler.assembleOAuth2AccessToken(accessToken, refreshToken);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		oauthAccessTokenRepository.deleteByTokenId(token.getValue());
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		try {
			storeAuthentication(authentication);
			OauthRefreshToken token = oauth2RefreshTokenAssembler.assembleOauthRefreshToken(refreshToken,
					authentication);
			oauthRefreshTokenRepository.save(token);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		OauthRefreshToken token = oauthRefreshTokenRepository.findByTokenId(tokenValue);
		return oauth2RefreshTokenAssembler.assembleOAuth2RefreshToken(token);
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		OauthRefreshToken refreshToken = oauthRefreshTokenRepository.findByTokenId(token.getValue());
		try {
			return restoreAuthentication(refreshToken.getAuthentication());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		oauthRefreshTokenRepository.deleteByTokenId(token.getValue());
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		oauthAccessTokenRepository.deleteByRefreshToken(refreshToken.getValue());
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		String code = oauth2AuthenticationAssembler.assembleAuthenticationCode(authentication);
		if (code == null) {
			return null;
		}
		OauthAccessToken accessToken = oauthAccessTokenRepository.findByAuthentication(code);
		OauthRefreshToken refreshToken = oauthRefreshTokenRepository.findByAuthentication(code);
		try {
			return oauth2AccessTokenAssembler.assembleOAuth2AccessToken(accessToken, refreshToken);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
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