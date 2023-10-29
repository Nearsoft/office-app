package com.encora.office.app.services;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.models.request.LoginRequest;
import com.encora.office.app.models.request.SignUpRequest;
import com.encora.office.app.models.response.AuthenticationResponse;

public interface AuthService {

    public AuthenticationResponse login(LoginRequest user);

    public AuthenticationResponse signup(SignUpRequest user);

    public AuthenticationResponse whoami();

//    public boolean forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

//    public AuthenticationResponse restorePassword(RestorePasswordRequest restorePasswordRequest);
}
