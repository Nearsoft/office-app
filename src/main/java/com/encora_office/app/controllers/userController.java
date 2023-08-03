package com.encora_office.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.encora_office.app.models.User;
/*
 * HTTP status code should be managed in here for best practices. 
 */
import com.encora_office.app.services.UserService;

@RestController
@RequestMapping("/api")
public class userController {

	@Autowired
	private final UserService userService;

	public userController(UserService userRepository) {
		this.userService = userRepository;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}

	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable String id) {
		return userService.findById(id);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		Optional<User> userToUpdate = getUser(id);

		System.out.println(userToUpdate);

		if (!userToUpdate.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(userService.updateUser(user));

	}
}
