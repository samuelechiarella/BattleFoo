package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.util.List;

import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.persistence.queriesInterfaces.ManagersQueries;

public class ManagersDAO implements ManagersQueries{

	private static ManagersDAO instance = null;
	private Connection connection;
	
	private ManagersDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static ManagersDAO getInstance(Connection c) {
		if(instance == null)
			instance = new ManagersDAO(c);
		return instance;
	}
	
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
