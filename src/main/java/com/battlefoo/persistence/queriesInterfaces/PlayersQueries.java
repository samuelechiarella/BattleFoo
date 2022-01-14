package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Player;

public interface PlayersQueries {
	public List<Player> getAll();
	public Player getByNickname(String nickname);
	public boolean exists(String nickname);
}
