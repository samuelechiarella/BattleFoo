package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Game;

public interface GamesQueries {
	public List<Game> getAll();
	public Game getByName(String name);
	public boolean exists(String name); 
	public List<Game> getGamesByGenre(String genre);
}
