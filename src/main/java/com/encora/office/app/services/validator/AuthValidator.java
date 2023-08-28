package com.encora.office.app.services.validator;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.encora.office.app.exception.ExceptionBuilder.buildServiceValidationException;

@Slf4j
@Component
@AllArgsConstructor
public class AuthValidator {

    private UserRepository userRepository;
    private Validator validator;

    private void validateNonExistingUsername(String email) {
        log.debug("Validate non existing user with email: {}", email);

        List<User> emails = userRepository.findByEmail(email);

        log.debug("Number of users with the same username: {}", emails.size());

        if (!emails.isEmpty()) {
            throw buildServiceValidationException("Email", "Unique");
        }
    }

    public void validateSignUpRequest(User user) {
        Set<ConstraintViolation<User>> errors = validator.validate(user);

        if (errors.size() > 0) {
            ConstraintViolation<User> constraintViolation = errors.iterator().next();

            String field = constraintViolation.getPropertyPath().toString();
            String constraint = constraintViolation.getMessage();

            throw buildServiceValidationException(field, constraint);
        }

        validateNonExistingUsername(user.getEmail());
        // validateValidEmail
        // validateMatchingPasswords();
        // validateStrongPassword();
        // validateValidRole();

    }

}
