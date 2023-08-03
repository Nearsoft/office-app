package com.encora_office.app.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

@JsonSerializableSchema
@Document(collection = "user")
public class User {

	@Id
	@GeneratedValue
	private String id;

	private String name;
	private String lastName;
	private String email;
	private String role;
	private boolean admin;

	// constructor

	public User(String name, String lastName, String email, String role, boolean admin) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.admin = admin;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getRole() {
		return this.role;
	}

	public String getId() {
		return this.id;
	}

	public String role() {
		return this.role;
	}

	public boolean admin() {
		return this.admin;
	}
}
