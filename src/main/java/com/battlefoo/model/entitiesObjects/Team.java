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
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Team {
	
	@NonNull
	private String teamName;
	
	@NonNull
	private String logo;
	
	private String description;
	
	@NonNull
	private Long leaderId;
	
	public Team(String teamName, String logo) {
		this.teamName = teamName;
		this.logo = logo;
	}


	@Override
	public int hashCode() {
		return Objects.hash(teamName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return Objects.equals(teamName, other.teamName);
	}
}
