package rrc.social.auth.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.OauthClientToken;

public interface OauthClientTokenRepository extends MongoRepository<OauthClientToken, String> {

}