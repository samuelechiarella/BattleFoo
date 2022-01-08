package com.battlefoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
	Connection conn;
	public Database() {
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://postgres:5432/postgres", "postgres", "postgres");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertGame(String game, String genre) {
		try {
			PreparedStatement st = conn.prepareStatement("insert into game values(?,?);");
			st.setString(1, game);
			st.setString(2, genre);
			System.out.println(st.executeUpdate()); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
