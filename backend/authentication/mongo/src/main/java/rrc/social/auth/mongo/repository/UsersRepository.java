package rrc.social.auth.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import rrc.social.auth.mongo.entidade.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	Users findByUsernameAndClientId(String username, String clientId);

}