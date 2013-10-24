package com.knowladgebase.model.entries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class LoginInfo {
	
	@Id
	@NotNull
	private String login;
	
	@NotNull
	private String password;
	
	public LoginInfo(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
}
