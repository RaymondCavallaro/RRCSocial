package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.OauthCode;

public interface OauthCodeRepository extends MongoRepository<OauthCode, String> {

}