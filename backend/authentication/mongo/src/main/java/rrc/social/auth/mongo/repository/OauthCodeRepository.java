package rrc.social.auth.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.OauthCode;

public interface OauthCodeRepository extends MongoRepository<OauthCode, String> {

	OauthCode findAllByCode(String authentication);

}