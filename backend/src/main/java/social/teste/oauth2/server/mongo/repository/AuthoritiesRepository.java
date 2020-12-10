package social.teste.oauth2.server.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.Authorities;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {

	List<Authorities> findByUsername(String username);

}