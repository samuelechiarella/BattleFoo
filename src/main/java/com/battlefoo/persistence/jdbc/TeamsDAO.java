package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		return null;
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
			System.out.println("ERROR IN TEAMS DAO EXISTS");
		}
		return false;
	}

	public boolean insert(Team team) {
		// example
		try {
			String query = "insert into teams(team_name,logo,leader_id) values(?,?,?);";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, team.getTeamName());
			ps.setString(2, "LOGO");
			ps.setLong(3, team.getLeaderId());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.out.println("ERROR IN TEAMS DAO INSERT");
		}
		return false;
	}
	
	private Team createTeam(ResultSet res) throws SQLException {
		Team t = null;
		t = new Team(res.getString("team_name"), res.getString("logo"), res.getLong("leader_id"));
		return t;
	}
}
