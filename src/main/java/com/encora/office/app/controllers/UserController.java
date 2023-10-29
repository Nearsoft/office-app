package com.encora.office.app.controllers;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.encora.office.app.constants.Constants.USERS;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(USERS)
public class UserController {

	private final UserServiceImpl userServiceImpl;

	@GetMapping
	public List<User> getUsers() {
		log.debug("Retrieve all users");

		return userServiceImpl.getUsers();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userServiceImpl.createUser(user));
	}

	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable String id) {
		return userServiceImpl.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		Optional<User> userToUpdate = getUser(id);

		log.debug("User to update: {}", userToUpdate);

		if (!userToUpdate.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(userServiceImpl.updateUser(user));
	}
}
