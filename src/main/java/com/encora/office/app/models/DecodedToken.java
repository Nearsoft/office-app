package com.encora.office.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecodedToken {
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("sub")
    private String sub;

    @JsonSerialize(as = Role.class)
    @JsonDeserialize(as = Role.class)
    @JsonProperty("role")
    private Role role;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("exp")
    private String exp;

    @JsonProperty("user")
    private String user;

    @JsonProperty("iat")
    private String iat;
}
