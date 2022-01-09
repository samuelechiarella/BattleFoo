package com.battlefoo.persistence.jdbc;

import java.util.List;

import com.battlefoo.model.Player;
import com.battlefoo.persistence.PlayersQueries;

public class PlayersDAO implements PlayersQueries {

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
