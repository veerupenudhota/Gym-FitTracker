package com.pvb.springboot.veeru.gym.dto;

import lombok.Data;

@Data
public class RegisterRequest {
  private String username;
  private String email;
  private String password;
  
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUsername() {
	// TODO Auto-generated method stub
	return null;
}
  
}