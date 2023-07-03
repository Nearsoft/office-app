package com.encora_office.app.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

@JsonSerializableSchema
@Document(collection = "user")
public class User {

	@Id
	private String id;

	private String name;
	private String lastName;
	private String email;
	private String role;
	private boolean admin;
	private PasswordEncoder password;

	// constructor

	public User(String name, String lastName, String email, String role, PasswordEncoder password, boolean admin) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
		this.admin = admin;
	}

	public String getFullName() {
		return name + " " + lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}
}
