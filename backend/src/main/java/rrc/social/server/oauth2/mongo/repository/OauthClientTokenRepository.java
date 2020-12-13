package rrc.social.server.oauth2.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.server.oauth2.mongo.entidade.OauthClientToken;

public interface OauthClientTokenRepository extends MongoRepository<OauthClientToken, String> {

}