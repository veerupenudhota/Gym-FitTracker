package com.pvb.springboot.veeru.gym.model;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name="app_user")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String email;
	    private String username;
	    private String password;
	    private String role;

	    // ✅ No-arg constructor (required by JPA)
	    public User() {}

	    // Optional: Parameterized constructor for convenience
	    public User(String email, String username, String password, String role) {
	        this.email = email;
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }

	    // ✅ Getters and Setters for all fields

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
	}