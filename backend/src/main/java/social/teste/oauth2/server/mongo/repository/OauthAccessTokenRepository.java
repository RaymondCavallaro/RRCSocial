package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.OauthAccessToken;

public interface OauthAccessTokenRepository extends MongoRepository<OauthAccessToken, String> {

	OauthAccessToken findByTokenId(String token);

	void deleteByTokenId(String token);

	void deleteByRefreshToken(String token);

	OauthAccessToken findByAuthentication(String code);

}