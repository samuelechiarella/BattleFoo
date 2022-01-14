package com.battlefoo.model.entitiesObjects;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Manager extends User {

	protected String managerId;

	public Manager(String nickname, String firstname, String lastname, String email, String manager_id) {
		super(nickname, firstname, lastname, email);
		this.managerId = manager_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(managerId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(managerId, other.managerId);
	}
}
