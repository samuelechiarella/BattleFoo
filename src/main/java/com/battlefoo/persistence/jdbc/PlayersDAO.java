package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.util.List;

import com.battlefoo.model.Player;
import com.battlefoo.persistence.PlayersQueries;

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
		// TODO Auto-generated method stub
		return null;
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

}
