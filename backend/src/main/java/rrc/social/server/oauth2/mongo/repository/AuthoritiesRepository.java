package rrc.social.server.oauth2.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.server.oauth2.mongo.entidade.Authorities;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {

	List<Authorities> findByUsername(String username);

}