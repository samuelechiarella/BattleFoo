package com.battlefoo.persistence.jdbc;

import java.util.List;

import com.battlefoo.model.Game;
import com.battlefoo.persistence.GamesQueries;

public class GamesDAO implements GamesQueries{

	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		return null;
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
