package com.battlefoo.persistence;

import java.util.List;

import com.battlefoo.model.Player;

public interface PlayersQueries {
	public List<Player> getAll();
	public Player getByNickname(String nickname);
	public boolean exists(String nickname);
}
