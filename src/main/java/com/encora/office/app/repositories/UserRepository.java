package com.encora.office.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.encora.office.app.models.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByEmail(String email);
}