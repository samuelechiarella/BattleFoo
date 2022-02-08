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
@Setter
@Getter
@ToString
public class Match {
	
	@NonNull
	private String firstTeam;
	@NonNull
	private String secondTeam;
	private String result;
	private Long tournamentId;
	@NonNull
	private Long matchId;
	@NonNull
	private Integer phase;
	private String chatHistory;
	
	@Override
	public int hashCode() {
		return Objects.hash(matchId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return Objects.equals(matchId, other.matchId);
	}
}
