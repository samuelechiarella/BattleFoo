package com.battlefoo.persistence;

import java.util.List;

import com.battlefoo.model.Team;

public interface TeamsQueries {
	public List<Team> getAll();
	public Team getByTeamName(String teamName);
	public boolean exists(String teamName);
}
