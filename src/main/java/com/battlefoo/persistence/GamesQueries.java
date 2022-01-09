package com.battlefoo.persistence;

import java.util.List;

import com.battlefoo.model.Game;

public interface GamesQueries {
	public List<Game> getAll();
	public Game getByName(String name);
	public boolean exists(String name); 
	public List<Game> getGamesByGenre(String genre);
}
