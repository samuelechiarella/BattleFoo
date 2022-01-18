package com.battlefoo.persistence.dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import com.battlefoo.DatabaseNames;
import com.battlefoo.model.entitiesObjects.*;
import com.battlefoo.persistence.jdbc.GamesDAO;
import com.battlefoo.persistence.jdbc.ManagersDAO;
import com.battlefoo.persistence.jdbc.PlayersDAO;
import com.battlefoo.persistence.jdbc.TeamsDAO;
import com.battlefoo.persistence.jdbc.TournamentsDAO;
import com.battlefoo.persistence.jdbc.UsersDAO;

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
	
	// ************************************* GAMES
	public List<Game> getAllGames(){
		return GamesDAO.getInstance(connection).getAll();
	}
	
	public Game getGameByName(String name) {
		return GamesDAO.getInstance(connection).getByName(name);
	}
	
	public boolean gameExists(String name) {
		return GamesDAO.getInstance(connection).exists(name);
	}
	
	public List<Game> getGamesByGenre(String genre){
		return GamesDAO.getInstance(connection).getGamesByGenre(genre);
	}
	
	// ************************************* PLAYERS
	public List<Player> getAllPlayers(){
		return PlayersDAO.getInstance(connection).getAll();
	}
	
	public Player getPlayerByNickname(String nickname) {
		return PlayersDAO.getInstance(connection).getByNickname(nickname);
	}
	
	public boolean playerExists(String name) {
		return PlayersDAO.getInstance(connection).exists(name);
	}
	
	// ************************************* MANAGERS
	public List<Manager> getAllManagers(){
		return ManagersDAO.getInstance(connection).getAll();
	}
	
	public Manager getManagerByNickname(String name) {
		return ManagersDAO.getInstance(connection).getByNickname(name);
	}
	
	public boolean managerExists(String nickname) {
		return ManagersDAO.getInstance(connection).exists(nickname);
	}
	
	// ************************************* Teams
	public List<Team> getAllTeams(){
		return TeamsDAO.getInstance(connection).getAll();
	}
	
	public Team getManagerByTeamName(String teamName) {
		return TeamsDAO.getInstance(connection).getByTeamName(teamName);
	}
	
	public boolean teamExists(String teamName) {
		return TeamsDAO.getInstance(connection).exists(teamName);
	}
	
	// ************************************* Tournaments
	public List<Tournament> getAllTournaments(){
		return TournamentsDAO.getInstance(connection).getAll();
	}
	
	public Tournament getManagerByTournamentName(String tournamentName) {
		return TournamentsDAO.getInstance(connection).getByTournamentName(tournamentName);
	}
	
	public boolean tournamentExists(String tournamentName) {
		return TournamentsDAO.getInstance(connection).exists(tournamentName);
	}

	public boolean insertTeam(Team team) {
		return TeamsDAO.getInstance(connection).insert(team);
	}
	
	// ************************************* Users
	
	public boolean insertUser(User user) {
		return UsersDAO.getInstance(connection).insertUser(user);
	}
	
	// ************************************* Auth
	public boolean allowLogIn(String username, String password) {
		return PlayersDAO.getInstance(connection).logUser(username, password);
	}
}
