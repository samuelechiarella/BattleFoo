package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.battlefoo.model.entitiesObjects.Game;
import com.battlefoo.persistence.queriesInterfaces.GamesQueries;

public class GamesDAO implements GamesQueries{

	private static GamesDAO instance = null;
	private Connection connection;
	
	private GamesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static GamesDAO getInstance(Connection c) {
		if(instance == null)
			instance = new GamesDAO(c);
		return instance;
	}
	
	@Override
	public List<Game> getAll() {
		List<Game> list = new ArrayList<Game>();
		try {
			String query = "select * from games;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				list.add(createGame(res));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Game getByName(String name) {
		Game g = null;
		try {
			String query = "select * from games where name=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				g = createGame(res);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public boolean exists(String name) {
		try {
			String query = "select * from games where name=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Game> getGamesByGenre(String genre) {
		List<Game> list = new ArrayList<Game>();;
		try {
			String query = "select * from games where genre=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, genre);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				list.add(createGame(res));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Game> getListByFilter(String filter) {
		List<Game> gamesListFiltered = null;
		String query = "select * from games where name like ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, filter);
			ResultSet res = ps.executeQuery();
			gamesListFiltered = new ArrayList<Game>();
			while(res.next())
				gamesListFiltered.add(createGame(res));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return gamesListFiltered;
	}
	
	private Game createGame(ResultSet res) throws SQLException {
		Game g = new Game(res.getString(1),res.getString(3));
		g.setGenre(res.getString(2));
		return g;
	}
}
