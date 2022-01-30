package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.battlefoo.model.entitiesObjects.Player;
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
		List<Team> list = new ArrayList<Team>();
		try {
			String query = "select * from teams;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				list.add(createTeam(res));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Team getByTeamName(String teamName) {
		Team t = null;
		try {
			String query = "select * from teams where team_name=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, teamName);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				t = createTeam(res);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public boolean exists(String teamName) {
		try {
			String query = "select * from teams where team_name=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, teamName);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN TEAMS DAO EXISTS");
		}
		return false;
	}

	@Override
	public boolean insert(Team team) {
		// example
		try {
			String query = "insert into teams(team_name,logo,leader_id) values(?,?,?);";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, team.getTeamName());
			ps.setString(2, team.getLogo());
			ps.setLong(3, team.getLeaderId());
			ps.execute();
			query = "insert into teams_members values(?,?)";
			ps = connection.prepareStatement(query);
			ps.setLong(1, team.getLeaderId());
			ps.setString(2, team.getTeamName());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.out.println("ERROR IN TEAMS DAO INSERT");
		}
		return false;
	}
	
	public List<Player> getTeamMembers(String teamName) {
		List<Player> l = null;
		try {
			String query = "select username from teams_members full outer join players on teams_members.player_id = players.player_id where team_name=?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, teamName);
			ResultSet res = ps.executeQuery();
			l = new ArrayList<Player>();
			while(res.next()) {
				l.add(PlayersDAO.getInstance(connection).getByUsername(res.getString("username")));
			}
		}
		catch(SQLException e) {
			System.out.println("ERROR IN TEAMS DAO GET TEAM MEMBERS");
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public List<Team> getAllByLeaderId(long leaderId) {
		List<Team> l = null;
		String query = "select * from teams where leader_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, leaderId);
			ResultSet res = ps.executeQuery();
			l = new ArrayList<Team>();
			while(res.next())
				l.add(createTeam(res));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN PLAYERS DAO GET ALL BY LEADER ID");
		}
		return l;
	}
	
	private Team createTeam(ResultSet res) throws SQLException {
		return new Team(res.getString("team_name"), res.getString("logo"), res.getString("description"), res.getLong("leader_id"));
	}

	@Override
	public boolean editDescription(Team team, String description) {
		String query = "update teams set description=? where team_name=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, description);
			ps.setString(2, team.getTeamName());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
