package com.encora.office.app.services;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.models.request.LoginRequest;
import com.encora.office.app.models.request.SignUpRequest;
import com.encora.office.app.models.response.AuthenticationResponse;
import com.encora.office.app.repositories.UserRepository;
import com.encora.office.app.services.validator.AuthValidator;
import com.encora.office.app.util.TokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.encora.office.app.exception.ExceptionBuilder.buildServiceValidationException;
import static com.encora.office.app.util.TokenUtil.generateRandomString;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private AuthValidator authValidator;
    private PasswordEncoder passwordEncoder;
    private TokenUtil tokenUtil;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        log.debug("Before running validations for loginRequest {}", loginRequest.getEmail());
        authValidator.validateLoginRequest(loginRequest);

        List<User> foundUsers = userRepository.findByEmail(loginRequest.getEmail());
        User authUser = foundUsers.get(0);

        String token = tokenUtil.buildJWT(authUser);

        return AuthenticationResponse.builder()
                .authToken(token)
                .user(authUser)
                .build();
    }

    @Override
    public AuthenticationResponse signup(SignUpRequest signUpRequest) {
        log.debug("Before running validations for signUpRequest: {}", signUpRequest.getEmail());
        authValidator.validateSignUpRequest(signUpRequest);

        log.debug("Build new user after validations");
        User newUser = User.builder()
                .name(signUpRequest.getName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .secret(generateRandomString())
                .dateCreated(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        User createdUser = userRepository.save(newUser);
        String token = tokenUtil.buildJWT(createdUser);

        log.debug("User {} created successfully with token: {}", createdUser.getEmail(), token);

        return AuthenticationResponse.builder()
                .user(newUser)
                .authToken(token)
                .build();
    }

    @Override
    public AuthenticationResponse whoami() {
        User user = (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (Objects.isNull(user)) {
            throw buildServiceValidationException("Token", "Auth");
        }

        return AuthenticationResponse.builder()
                .user(user)
                .build();
    }

//    public boolean forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
//        String email = forgotPasswordRequest.getEmail();
//        authValidator.validateForgotPasswordEmail(forgotPasswordRequest);
//
//        List<User> foundUsers = userRepository.findByEmail(email);
//        User user = foundUsers.get(0);
//
//        String randomToken = generateRandomTokenWithExp();
//
//        PasswordRestore passwordRestore = PasswordRestore.builder()
//                .email(email)
//                .token(token)
//                .dateCreated(LocalDateTime.now())
//                .lastUpdated(LocalDateTime.now())
//                .userId(user.getId());
//
//        PasswordRestore createdPasswordRestore = passwordRestoreRepository.save(passwordRestore);
//
//        Map<String, String> dataMap = buildEmailDataMap(Constants.RESET_PASSWORD, createdPasswordRestore);
//
//        boolean emailSent = emailProducer.sendTemplateEmail(Constants.RESET_PASSWORD, dataMap);
//
//        return emailSent;
//    }

//        public AuthenticationResponse restorePassword(RestorePasswordRequest restorePasswordRequest) {
//            authValidator.validaterestorePasswordRequest(restorePasswordRequest);
//            PasswordRestore passwordRestore = passwordRestoreRepository.findByToken(restorePasswordRequest);
//
//            // Validate token is valid (it exists and is not expired)
//            // Update user's password
//        }
}


