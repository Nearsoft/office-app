package com.encora.office.app.services;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * The service layer is where all the business logic is written.
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public Optional<User> findById(String id) {
		log.debug("Trying to retrieve user with id: {}", id);
		return userRepository.findById(id);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
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