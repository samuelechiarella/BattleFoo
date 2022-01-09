package com.battlefoo.model;

import java.util.Objects;

public class User {
	
	protected String nickname;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String password;
	
	public User(String nickname, String firstname, String lastname, String email, String password) {
		super();
		this.nickname = nickname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(nickname, other.nickname);
	}
}
