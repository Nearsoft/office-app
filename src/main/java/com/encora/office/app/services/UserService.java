package com.encora.office.app.services;

import com.encora.office.app.models.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User createUser(User user);

    User updateUser(User user);
}
