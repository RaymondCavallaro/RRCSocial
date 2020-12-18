package rrc.social.auth.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.OauthClientDetails;

public interface OauthClientDetailsRepository extends MongoRepository<OauthClientDetails, String> {

	OauthClientDetails findByClientId(String clientId);

}