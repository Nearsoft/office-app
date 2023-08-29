package com.encora.office.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.services.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.encora.office.app.constants.Resources.AUTH;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private AuthService authService;

    @PostMapping(path = "/singup")
    public ResponseEntity<User> singup(@RequestBody User user) {
        log.debug("Singup user with provided data: {}", user.getEmail());

        User createdUser = authService.singup(user);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

}
