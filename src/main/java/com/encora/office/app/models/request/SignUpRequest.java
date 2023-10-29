package com.encora.office.app.models.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import com.encora.office.app.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class SignUpRequest {
    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Role role;

    @NotNull
    private String password;
}
