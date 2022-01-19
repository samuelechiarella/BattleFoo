package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.battlefoo.DatabaseNames;
import com.battlefoo.model.entitiesObjects.Player;
import com.battlefoo.persistence.queriesInterfaces.PlayersQueries;

public class PlayersDAO implements PlayersQueries {

	private static PlayersDAO instance = null;
	private Connection connection;
	
	private PlayersDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static PlayersDAO getInstance(Connection c) {
		if(instance == null)
			instance = new PlayersDAO(c);
		return instance;
	}
	
	@Override
	public List<Player> getAll() {
		List<Player> list = new ArrayList<Player>();
		try {
			String query = "select * from players";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				list.add(createPlayer(res));
			}
			
		} catch (SQLException e) {
			System.out.println("NO");
		}
		return list;
	}

	@Override
	public Player getByUsername(String username) {
		Player p = null;
		String query = "select * from players where username=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				p = createPlayer(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public boolean exists(String username) {
		try {
			String query = "select * from players where username=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN PLAYERS DAO EXISTS");
		}
		return false;
	}

	public boolean logUser(String username, String password) {
		String query = "select username from users where username=? and password=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN PLAYERS DAO LOG USER");
		}
		return false;
	}
	
	private Player createPlayer(ResultSet res) throws SQLException {
		Player p = null;
		String query = "select * from players join users on players.username = users.username where players.username=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, res.getString("username"));
		ResultSet thisRes = ps.executeQuery();
		if(thisRes.next())
			p = new Player(thisRes.getString(DatabaseNames.Tables.Users.COLUMN_USERNAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_FIRST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_LAST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_EMAIL),
							thisRes.getLong(DatabaseNames.Tables.Players.COLUMN_PLAYER_ID),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_PROFILE_PICTURE));
		return p;
	}

}
