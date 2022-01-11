package com.battlefoo.model.entitiesObjects;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class User {
	
	protected String password;
	
	@NonNull
	protected String nickname;
	
	@NonNull
	protected String firstName;
	
	@NonNull
	protected String lastName;
	
	@NonNull
	protected String email;

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
