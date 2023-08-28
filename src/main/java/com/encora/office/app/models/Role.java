package com.encora.office.app.models;

public enum Role {

    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}
