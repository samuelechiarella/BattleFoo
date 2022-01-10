package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		List<Game> list = new ArrayList<>();
		try {
			String query = "select * from games";
			Statement ps = connection.createStatement();
			ResultSet res = ps.executeQuery(query);
			while(res.next()) {
				Game g = new Game(res.getString(1));
				if(res.getString(2) != null) {
					g.setGenre(res.getString(2));
				}
				list.add(g);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Game getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Game> getGamesByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

}
