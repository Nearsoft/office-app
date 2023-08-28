package com.encora.office.app.services;

import java.util.Optional;

import com.encora.office.app.models.entity.User;

public interface IAuthService {

    public User login(User user);

    public User singup(User user);

    public User whoami();

    public void forgotPassword(User user);

    // public Optional<User> restorePassword(RestorePasswordRequest
    // restorePasswordRequest);
}
