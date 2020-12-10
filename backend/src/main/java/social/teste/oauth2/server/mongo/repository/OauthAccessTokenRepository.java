package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.OauthAccessToken;

public interface OauthAccessTokenRepository extends MongoRepository<OauthAccessToken, String> {

	OauthAccessToken findByToken(byte[] bytes);

	void deleteByToken(byte[] bytes);

}