package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.battlefoo.DatabaseNames;
import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.model.entitiesObjects.Organization;
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
			String query = "select * from managers;";
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
	public Manager getByUsername(String username) {
		Manager m = null;
		try {
			String query = "select * from managers where username=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			if(res.next())
				m = createManager(res);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	@Override
	public Manager getById(Long managerId) {
		Manager m = null;
		try {
			String query = "select * from managers where manager_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, managerId);
			ResultSet res = ps.executeQuery();
			if(res.next())
				m = createManager(res);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public boolean exists(String username) {
		try {
			String query = "select * from managers where username=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
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
		String query = "select * from managers join users on managers.username = users.username where managers.username=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, res.getString("username"));
		ResultSet thisRes = ps.executeQuery();
		if(thisRes.next())
			m = new Manager(thisRes.getString(DatabaseNames.Tables.Users.COLUMN_USERNAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_FIRST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_LAST_NAME),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_EMAIL),
							thisRes.getString(DatabaseNames.Tables.Users.COLUMN_PROFILE_PICTURE),
							thisRes.getLong(DatabaseNames.Tables.Managers.COLUMN_MANAGER_ID));
		return m;
	}

	@Override
	public boolean insertManager(String username) {
		String query = "insert into managers(username) values(?);";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isMemberOf(Organization organization, String membersUsername) {
		Manager m = getByUsername(membersUsername);
		String query = "select * from organizations_members where manager_id=? and organization_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, m.getManagerId());
			ps.setLong(2, organization.getOrganizationId());
			if(ps.executeQuery().next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
