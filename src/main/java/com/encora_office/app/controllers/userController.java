package com.encora_office.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	// @PostMapping("/user")
	// public ResponseEntity<?> createUser(@RequestBody User user) throws Exception
	// {
	// UserService.save(user);
	// return ResponseEntity.ok().build();
	// }

	// @GetMapping("/user/{id}")
	// public Optional<User> getUser(String id) {
	// return UserService.findById(id);
	// }

	// @PutMapping("/user/{id}")
	// public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody
	// User user) {
	// List<User> userToUpdate = UserService.findById(id);

	// return ResponseEntity.ok().build();

	// }
}
