package rrc.social.server.oauth2.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.server.oauth2.mongo.entidade.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	Users findByUsernameAndClientId(String username, String clientId);

}