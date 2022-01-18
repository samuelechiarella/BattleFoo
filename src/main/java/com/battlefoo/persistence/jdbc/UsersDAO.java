package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.battlefoo.model.entitiesObjects.User;
import com.battlefoo.persistence.queriesInterfaces.UsersQueries;

public class UsersDAO implements UsersQueries {

	private static UsersDAO instance = null;
	private Connection connection;
	
	private UsersDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static UsersDAO getInstance(Connection c) {
		if(instance == null)
			instance = new UsersDAO(c);
		return instance;
	}
	
	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String email) {
		try {
			String query = "select * from users where email=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private User createUser(ResultSet res) throws SQLException{
		User u = new User(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
		return u;
	}

	@Override
	public boolean insertUser(User user) {
		try {
			String query = "insert into users values(?,?,?,?,?);";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN USERS DAO INSERT USER");
		}
		return false;
	}

}
