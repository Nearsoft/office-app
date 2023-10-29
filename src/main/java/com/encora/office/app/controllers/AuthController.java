package com.encora.office.app.controllers;

import com.encora.office.app.models.request.LoginRequest;
import com.encora.office.app.models.request.SignUpRequest;
import com.encora.office.app.models.response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.encora.office.app.services.AuthServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.encora.office.app.constants.Constants.AUTH;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private AuthServiceImpl authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        log.debug("signup user with provided data: {}", signUpRequest.getEmail());

        AuthenticationResponse signUpResponse = authService.signup(signUpRequest);
        log.debug("signUpResponse {}", signUpResponse.toString());

        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        log.debug("Login try for user {}", loginRequest.getEmail());

        AuthenticationResponse loginResponse = authService.login(loginRequest);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/whoami")
    public ResponseEntity<AuthenticationResponse> whoami() {
        AuthenticationResponse loginResponse = authService.whoami();

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
