package com.battlefoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.battlefoo.model.DatabaseNames;

public class Database {
	private Connection connection;
	
	private static Database instance = null;
	
	private Database() {
		try {
			connection = DriverManager.getConnection(DatabaseNames.URL_POSTGRES, "postgres", "postgres");
			System.out.println("Connected to postgres");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance() {
		if(instance == null)
			instance = new Database();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
