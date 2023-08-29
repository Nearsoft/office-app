package com.encora.office.app.services;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.repositories.UserRepository;

/*
 * The service layer is where all the business logic is written.
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Optional<User> findById(String id) {
		log.debug("Trying to retrieve user with id: {}", id);
		return userRepository.findById(id);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		Optional<User> userToUpdate = userRepository.findById(user.getId());
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}