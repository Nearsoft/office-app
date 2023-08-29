package com.encora.office.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.encora.office.app.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@Document("users")
public class User {

	@Id
	private String id;

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
	private boolean admin;

	private String secret;

	@JsonIgnore
	private String password;

	// Timestamps
	@CreatedDate
	private OffsetDateTime dateCreated;

	@LastModifiedDate
	private OffsetDateTime lastUpdated;
}
