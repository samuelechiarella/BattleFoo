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
public class Tournament {
	
	private String description;
	private String rules;
	private String schedule;
	private String sponsor;
	private String prizes;
	
	@NonNull
	private String tournamentId;
	
	@NonNull
	private String name;
	
	@NonNull
	private Date date;
	
	@NonNull
	private String game_name;
	
	@NonNull
	private String logo;
	
	@NonNull
	private Long managerId;

	@Override
	public int hashCode() {
		return Objects.hash(tournamentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		return Objects.equals(tournamentId, other.tournamentId);
	}
}
