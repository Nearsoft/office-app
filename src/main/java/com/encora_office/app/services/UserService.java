package com.encora_office.app.services;

import java.util.*;

import com.encora_office.app.models.User;
import com.encora_office.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * The service layer is where all the business logic is written.
 */
@Service
public class UserService {

	private String name;
	private String email;
	private String password;
	private String role;

	// Dependency injection
	@Autowired
	private final UserRepository userRepository;

	// public UserService(UserRepository userRepository) {
	// this.userRepository = userRepository;
	// }

	public UserService(UserRepository userRepository) {
		// super();
		// this.name = name;
		// this.email = email;
		// this.password = password;
		// this.role = role;
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

}