package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.battlefoo.model.entitiesObjects.Match;
import com.battlefoo.persistence.queriesInterfaces.MatchesQueries;

public class MatchesDAO implements MatchesQueries {

	private static MatchesDAO instance = null;
	private Connection connection;
	
	private MatchesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static MatchesDAO getInstance(Connection c) {
		if(instance == null)
			instance = new MatchesDAO(c);
		return instance;
	}
	
	@Override
	public List<Match> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Match> getAllByTournamentId(Long tounamentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Match getMatch(String team1, String team2, Long tournamentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getTeamsByPhase(int numeroPartite, Long tournamentId) {
		String[][] teams = null;
		String query = "select first_team, second_team from matches where phase=? and tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, numeroPartite);
			ps.setLong(2, tournamentId);
			teams = new String[numeroPartite][2];
			ResultSet res = ps.executeQuery();
			int count = 0;
			while(res.next()) {
				teams[count][0] = res.getString(1);
				teams[count][1] = res.getString(2);
				count++;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return teams;
	}

}
