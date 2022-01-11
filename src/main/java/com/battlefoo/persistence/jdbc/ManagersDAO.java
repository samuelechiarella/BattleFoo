package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.battlefoo.DatabaseNames;
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
		List<Manager> list = new ArrayList<Manager>();
		try {
			String query = "select * from managers";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				list.add(createManager(res));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Manager getByNickname(String nickname) {
		Manager m = null;
		try {
			String query = "select * from managers where nickname=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nickname);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				m = createManager(res);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public boolean exists(String nickname) {
		try {
			String query = "select * from managers where name=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nickname);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Manager createManager(ResultSet res) throws SQLException {
		Manager m = null;
		String query = "select * from managers join users on managers.nickname = users.nickname where managers.nickname=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, res.getString("nickname"));
		ResultSet thisRes = ps.executeQuery();
		while(thisRes.next())
			m = new Manager(thisRes.getString(DatabaseNames.Tables.Users.COLUMN_NICKNAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_FIRST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_LAST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_EMAIL),
							thisRes.getString(DatabaseNames.Tables.Managers.COLUMN_MANAGER_ID));
		return m;
	}
}
