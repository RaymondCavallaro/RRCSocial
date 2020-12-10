package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.OauthClientToken;

public interface OauthClientTokenRepository extends MongoRepository<OauthClientToken, String> {

}