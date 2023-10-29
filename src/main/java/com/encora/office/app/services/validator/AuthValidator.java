package com.encora.office.app.services.validator;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.encora.office.app.models.request.LoginRequest;
import com.encora.office.app.models.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.encora.office.app.models.Role;
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
    private PasswordEncoder passwordEncoder;

    public void validateSignUpRequest(SignUpRequest signUpRequest) {
        Set<ConstraintViolation<SignUpRequest>> errors = validator.validate(signUpRequest);

        if (!errors.isEmpty()) {
            ConstraintViolation<SignUpRequest> constraintViolation = errors.iterator().next();

            String field = constraintViolation.getPropertyPath().toString();
            String constraint = constraintViolation.getMessage();

            throw buildServiceValidationException(field, constraint);
        }

        validateNonExistingUser(signUpRequest.getEmail());
        validateStrongPassword(signUpRequest.getPassword());
        validateValidEmail(signUpRequest.getEmail());
        validateValidRole(signUpRequest.getRole());
    }

    public void validateLoginRequest(LoginRequest loginRequest) {
        Set<ConstraintViolation<LoginRequest>> errors = validator.validate(loginRequest);

        if (!errors.isEmpty()) {
            ConstraintViolation<LoginRequest> constraintViolation = errors.iterator().next();

            String field = constraintViolation.getPropertyPath().toString();
            String constraint = constraintViolation.getMessage();

            throw buildServiceValidationException(field, constraint);
        }

        User user = validateExistingUser(loginRequest.getEmail());
        validatePasswordMatches(loginRequest.getPassword(), user.getPassword());
    }

    private User validateExistingUser(String email) {
        List<User> users = userRepository.findByEmail(email);

        if (users.isEmpty()) {
            throw buildServiceValidationException("Email", "Exist");
        }

        return users.get(0);
    }

    private void validatePasswordMatches(String password, String encryptedPassword) {
        if (!passwordEncoder.matches(password, encryptedPassword)) {
            throw buildServiceValidationException("Password", "Match");
        }
    }

    private void validateNonExistingUser(String email) {
        log.debug("Validate non existing user with email: {}", email);

        List<User> users = userRepository.findByEmail(email);
        log.debug("Number of users with the same username: {}", users.size());

        if (!users.isEmpty()) {
            throw buildServiceValidationException("Email", "Unique");
        }
    }

    private void validateStrongPassword(String password) {
        log.debug("Validate password strength");
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=.!])"
                       + "(?=\\S+$).{8,100}$";

        boolean passwordMatches = patternMatches(password, regex);

       if (Objects.isNull(password) || !passwordMatches) {
            throw buildServiceValidationException("Password", "Strength");
       }
    }

    private void validateValidEmail(String email) {
        log.debug("Validate email format");
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        boolean emailMatches = patternMatches(email, regex);

       if (Objects.isNull(email) || !emailMatches) {
            throw buildServiceValidationException("Email", "Valid");
       }
    }

    private void validateValidRole(Role role) {
        log.debug("Validate user provided role: {}", role.getRoleName());
        if (!Role.USER.equals(role)) {
            throw buildServiceValidationException("Role", "Valid");
        }
    }

    public static boolean patternMatches(String input, String regexPattern) {
        return Pattern.compile(regexPattern)
          .matcher(input)
          .matches();
    }

}
