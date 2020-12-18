package rrc.social.auth.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.Authorities;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {

	List<Authorities> findByUsername(String username);

}