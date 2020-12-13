package rrc.social.server.oauth2.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.server.oauth2.mongo.entidade.OauthCode;

public interface OauthCodeRepository extends MongoRepository<OauthCode, String> {

	OauthCode findAllByCode(String authentication);

}