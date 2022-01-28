package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Team;

public interface TeamsQueries {
	public List<Team> getAll();
	public Team getByTeamName(String teamName);
	public boolean exists(String teamName);
	List<Team> getAllByLeaderId(long leaderId);
	public boolean insert(Team team);
	public boolean editDescription(Team team, String description);
}
