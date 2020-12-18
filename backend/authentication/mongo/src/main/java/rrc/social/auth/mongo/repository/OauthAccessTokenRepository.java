package rrc.social.auth.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.OauthAccessToken;

public interface OauthAccessTokenRepository extends MongoRepository<OauthAccessToken, String> {

	OauthAccessToken findByTokenId(String token);

	void deleteByTokenId(String token);

	void deleteByRefreshToken(String token);

	OauthAccessToken findByAuthentication(String code);

}