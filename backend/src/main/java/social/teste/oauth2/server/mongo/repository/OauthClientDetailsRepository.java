package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.OauthClientDetails;

public interface OauthClientDetailsRepository extends MongoRepository<OauthClientDetails, String> {

	OauthClientDetails findByClientId(String clientId);

}