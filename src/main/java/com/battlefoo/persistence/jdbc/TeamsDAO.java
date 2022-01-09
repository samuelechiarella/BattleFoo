package com.battlefoo.persistence.jdbc;

import java.util.List;

import com.battlefoo.model.Team;
import com.battlefoo.persistence.TeamsQueries;

public class TeamsDAO implements TeamsQueries {

	@Override
	public List<Team> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getByTeamName(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String teamName) {
		// TODO Auto-generated method stub
		return false;
	}

}
