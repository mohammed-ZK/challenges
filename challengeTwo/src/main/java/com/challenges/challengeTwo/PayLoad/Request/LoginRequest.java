package com.challenges.challengeTwo.PayLoad.Request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message = "the name is blank")
	private String username;

	@NotBlank(message = "the password is blank")
	private String password;

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

	public LoginRequest() {
		super();
	}
	
}