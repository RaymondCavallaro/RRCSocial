package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.OauthRefreshToken;

public interface OauthRefreshTokenRepository extends MongoRepository<OauthRefreshToken, String> {

}