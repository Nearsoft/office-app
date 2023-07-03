package com.encora_office.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.encora_office.app.models.User;

@Repository
// Need to understand better how the connection with MongoDB works.
public interface UserRepository extends MongoRepository<User, String> {
	User findById(Integer id);

	List<User> findAll();

	// User createUser(User user);

	// void deleteUser(User user);

}