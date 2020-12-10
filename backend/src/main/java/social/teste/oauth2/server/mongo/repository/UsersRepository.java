package social.teste.oauth2.server.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import social.teste.oauth2.server.mongo.entidade.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	Users findByUsername(String username);

}