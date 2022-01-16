package com.battlefoo.model.entitiesObjects;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
	
	@NonNull
	protected String username;
	
	@NonNull
	protected String firstName;
	
	@NonNull
	protected String lastName;
	
	@NonNull
	protected String email;
	
	protected String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(username);
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
		return Objects.equals(username, other.username);
	}
}
