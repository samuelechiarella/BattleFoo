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
public class Player extends User {

	private String playerId;
	
	public Player(String nickname, String firstname, String lastname, String email, String player_id) {
		super(nickname, firstname, lastname, email);
		this.playerId = player_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(playerId);
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
		Player other = (Player) obj;
		return Objects.equals(playerId, other.playerId);
	}
}
