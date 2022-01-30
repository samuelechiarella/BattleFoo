package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.battlefoo.model.entitiesObjects.Manager;
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
	
	@Override
	public Tournament getByCreatorUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertTournament(Tournament tournament) {
		String query = "insert into tournaments values()";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, tournament.getName());
			ps.setString(2, tournament.getDate().getDate());
			ps.setString(3, tournament.getDescription());
			ps.setString(4, tournament.getRules());
			ps.setString(5, tournament.getSchedule());
			ps.setString(6, tournament.getGameName());
			ps.setString(7, tournament.getSponsor());
			ps.setString(8, tournament.getLogo());
			ps.setLong(9, tournament.getManagerId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
