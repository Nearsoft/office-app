package com.encora.office.app.services;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.repositories.UserRepository;
import com.encora.office.app.services.validator.AuthValidator;

import static com.encora.office.app.util.TokenUtil.generateRandomString;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private UserRepository userRepository;
    private AuthValidator authValidator;

    @Override
    public User login(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public User singup(User user) {
        authValidator.validateSignUpRequest(user);

        user.setSecret(generateRandomString());

        User createdUser = userRepository.save(user);

        return createdUser;

        // Generate random safe secret
    }

    @Override
    public User whoami() {
        throw new UnsupportedOperationException("Unimplemented method 'whoami'");
    }

    @Override
    public void forgotPassword(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'forgotPassword'");
    }

}
