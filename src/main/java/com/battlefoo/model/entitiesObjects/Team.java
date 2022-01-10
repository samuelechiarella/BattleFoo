package com.battlefoo.model.entitiesObjects;

import java.util.Objects;

public class Team {
	
	private String teamName;
	private String logo;
	private String description;
	private long leaderId;
	private long tournamentId;
	
	public Team(String team_name, String logo, long leader_id) {
		super();
		this.teamName = team_name;
		this.logo = logo;
		this.leaderId = leader_id;
	}

	public String getTeam_name() {
		return teamName;
	}

	public void setTeam_name(String team_name) {
		this.teamName = team_name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getLeader_id() {
		return leaderId;
	}

	public void setLeader_id(long leader_id) {
		this.leaderId = leader_id;
	}

	public long getTournament_id() {
		return tournamentId;
	}

	public void setTournament_id(long tournament_id) {
		this.tournamentId = tournament_id;
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
