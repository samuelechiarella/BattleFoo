package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.battlefoo.DatabaseNames;
import com.battlefoo.model.entitiesObjects.Manager;
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
	public Player getByNickname(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String nickname) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Player createPlayer(ResultSet res) throws SQLException {
		Player p = null;
		String query = "select * from players join users on players.nickname = users.nickname where players.nickname=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, res.getString("nickname"));
		ResultSet thisRes = ps.executeQuery();
		while(thisRes.next())
			p = new Player(thisRes.getString(DatabaseNames.Tables.Users.COLUMN_NICKNAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_FIRST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_LAST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_EMAIL),
							thisRes.getString(DatabaseNames.Tables.Players.COLUMN_PLAYER_ID));
		return p;
	}

}
