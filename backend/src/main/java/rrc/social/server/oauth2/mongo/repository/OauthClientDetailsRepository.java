package rrc.social.server.oauth2.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.server.oauth2.mongo.entidade.OauthClientDetails;

public interface OauthClientDetailsRepository extends MongoRepository<OauthClientDetails, String> {

	OauthClientDetails findByClientId(String clientId);

}