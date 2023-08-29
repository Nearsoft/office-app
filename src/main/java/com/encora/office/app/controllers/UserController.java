package com.encora.office.app.controllers;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.services.UserService;

import static com.encora.office.app.constants.Resources.USERS;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(USERS)
public class UserController {

	private final UserService userService;

	@GetMapping
	public List<User> getUsers() {
		log.debug("Retrieve all users");

		return userService.getUsers();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}

	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable String id) {
		return userService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		Optional<User> userToUpdate = getUser(id);

		log.debug("User to update: {}", userToUpdate);

		if (!userToUpdate.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(userService.updateUser(user));
	}
}
