package com.pvb.springboot.veeru.gym.dto;


import lombok.Data;

@Data
public class LoginRequest {
  private String email;
  private String password;
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
