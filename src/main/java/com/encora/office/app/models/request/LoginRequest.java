package com.encora.office.app.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
