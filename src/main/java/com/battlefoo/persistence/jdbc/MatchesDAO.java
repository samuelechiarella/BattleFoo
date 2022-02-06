package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		List<Match> matches = null;
		String query = "select matches.* from matches,tournaments where matches.tournament_id=tournaments.tournament_id "
						+ "and tournaments.tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tounamentId);
			ResultSet res = ps.executeQuery();
			matches = new ArrayList<Match>();
			while(res.next())
				matches.add(createMatch(res));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return matches;
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

	@Override
	public String getChatByMatchId(Long matchId) {
		String query = "select chat_history from matches where match_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, matchId);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return res.getString("chat_history");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean setChatHistoryByMatchId(String chatHistory, Long matchId) {
		String query = "update matches set chat_history=? where match_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, chatHistory);
			ps.setLong(2, matchId);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Match getMatchById(Long matchId) {
		Match m = null;
		String query = "select * from matches where match_id=?;";
		try{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, matchId);
			ResultSet res = ps.executeQuery();
			if(res.next())
				m = createMatch(res);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public List<Long> getMatchLongAttendeesByMatchId(Long matchId) {
		List<Long> l = null;
		String query = "select teams_members.player_id "
				+ "from matches,tournaments,tournaments_attendees,teams,teams_members "
				+ "where matches.tournament_id = tournaments.tournament_id "
				+ "and tournaments.tournament_id = tournaments_attendees.tournament_id "
				+ "and tournaments_attendees.team_name = teams.team_name "
				+ "and teams.team_name = teams_members.team_name "
				+ "and matches.match_id =? "
				+ "and (matches.first_team = teams_members.team_name or matches.second_team = teams_members.team_name);";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, matchId);
			ResultSet res = ps.executeQuery();
			l = new ArrayList<Long>();
			while(res.next())
				l.add(res.getLong(1));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	private Match createMatch(ResultSet res) throws SQLException{
		return new Match(res.getString(1),res.getString(2),res.getString(3),res.getLong(4),res.getLong(5),
						res.getInt(6),res.getString(7));
	}

	@Override
	public boolean insertMatch(Match m) {
		String query = "insert into matches(first_team,second_team,result,tournament_id,phase,chat_history) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, m.getFirstTeam());
			ps.setString(2, m.getSecondTeam());
			ps.setString(3, m.getResult());
			ps.setLong(4, m.getTournamentId());
			ps.setInt(5, m.getPhase());
			ps.setString(6, m.getChatHistory());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
