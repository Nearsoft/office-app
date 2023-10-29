package com.encora.office.app.models.response;

import com.encora.office.app.models.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationResponse {
    private User user;
    private String authToken;
}
