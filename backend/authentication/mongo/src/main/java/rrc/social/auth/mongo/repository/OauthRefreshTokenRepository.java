package rrc.social.auth.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.OauthRefreshToken;

public interface OauthRefreshTokenRepository extends MongoRepository<OauthRefreshToken, String> {

	OauthRefreshToken findByTokenId(String refreshToken);

	void deleteByTokenId(String token);

	OauthRefreshToken findByAuthentication(String code);

}