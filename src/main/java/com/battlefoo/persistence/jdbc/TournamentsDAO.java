package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.util.List;

import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.queriesInterfaces.TournamentsQueries;

public class TournamentsDAO  implements TournamentsQueries{

	private static TournamentsDAO instance = null;
	private Connection connection;
	
	private TournamentsDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static TournamentsDAO getInstance(Connection c) {
		if(instance == null)
			instance = new TournamentsDAO(c);
		return instance;
	}
	
	@Override
	public List<Tournament> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tournament getByTournamentName(String tournamentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String tournamentName) {
		// TODO Auto-generated method stub
		return false;
	}

}
