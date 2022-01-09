package com.battlefoo.persistence.jdbc;

import java.util.List;

import com.battlefoo.model.Manager;
import com.battlefoo.persistence.ManagersQueries;

public class ManagersDAO implements ManagersQueries{

	@Override
	public List<Manager> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager getByNickname(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

}
