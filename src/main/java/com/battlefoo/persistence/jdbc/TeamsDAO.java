package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.util.List;

import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.queriesInterfaces.TeamsQueries;

public class TeamsDAO implements TeamsQueries {

	private static TeamsDAO instance = null;
	private Connection connection;
	
	private TeamsDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static TeamsDAO getInstance(Connection c) {
		if(instance == null)
			instance = new TeamsDAO(c);
		return instance;
	}
	
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
