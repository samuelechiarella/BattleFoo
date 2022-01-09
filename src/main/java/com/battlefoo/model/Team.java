package com.battlefoo.model;

import java.util.Objects;

public class Team {
	
	private String team_name;
	private String logo;
	private String description;
	private long leader_id;
	private long tournament_id;
	
	public Team(String team_name, String logo, long leader_id) {
		super();
		this.team_name = team_name;
		this.logo = logo;
		this.leader_id = leader_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
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
		return leader_id;
	}

	public void setLeader_id(long leader_id) {
		this.leader_id = leader_id;
	}

	public long getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(long tournament_id) {
		this.tournament_id = tournament_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(team_name);
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
		return Objects.equals(team_name, other.team_name);
	}
}
