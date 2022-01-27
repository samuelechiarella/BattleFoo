package com.battlefoo.persistence.dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import com.battlefoo.DatabaseNames;
import com.battlefoo.model.entitiesObjects.*;
import com.battlefoo.persistence.jdbc.GamesDAO;
import com.battlefoo.persistence.jdbc.ManagersDAO;
import com.battlefoo.persistence.jdbc.OrganizationsDAO;
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
	
	public Player getPlayerByUsername(String username) {
		return PlayersDAO.getInstance(connection).getByUsername(username);
	}
	
	public boolean playerExists(String name) {
		return PlayersDAO.getInstance(connection).exists(name);
	}
	
	// ************************************* MANAGERS
	public List<Manager> getAllManagers(){
		return ManagersDAO.getInstance(connection).getAll();
	}
	
	public Manager getManagerByUsername(String username) {
		return ManagersDAO.getInstance(connection).getByUsername(username);
	}
	
	public boolean managerExists(String username) {
		return ManagersDAO.getInstance(connection).exists(username);
	}
	
	// ************************************* Teams
	public List<Team> getAllTeams(){
		return TeamsDAO.getInstance(connection).getAll();
	}
	
	public Team getTeamByTeamName(String teamName) {
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

	public List<Player> getAllTeamMembers(String teamName) {
		return TeamsDAO.getInstance(connection).getTeamMembers(teamName);
	}

	public boolean insertOrganizationMember(String creatorUsername, String organizationName, String membersUsername) {
		Manager creator = ManagersDAO.getInstance(connection).getByUsername(creatorUsername);
		Organization currentOrg = null;
		for(Organization o : OrganizationsDAO.getInstance(connection).getAllByCreatorId(creator.getManagerId()))
			if(o.getOrganizationName().compareTo(organizationName)==0)
				currentOrg = o;
		if(currentOrg == null)
			return false;
		Manager newMember = ManagersDAO.getInstance(connection).getByUsername(membersUsername);
		return OrganizationsDAO.getInstance(connection).insertMember(creator, currentOrg, newMember);
	}

	public List<Team> getTeamsByPlayer(String leaderUsername) {
		Player leader = PlayersDAO.getInstance(connection).getByUsername(leaderUsername);
		return TeamsDAO.getInstance(connection).getAllByLeaderId(leader.getPlayerId());
	}

	public Manager getManagerById(Long creatorId) {
		return ManagersDAO.getInstance(connection).getById(creatorId);
	}
	
	public List<Manager> getOrganizationMembersById(Long id) {
		return OrganizationsDAO.getInstance(connection).getMembersByOrganizationId(id);
	}

	public List<Organization> getMyOrganizations(String username) {
		Manager manager = Database.getInstance().getManagerByUsername(username);
		return OrganizationsDAO.getInstance(connection).getAllByManagerId(manager.getManagerId());
	}

	public boolean insertManager(String username) {
		return ManagersDAO.getInstance(connection).insertManager(username);
	}

	public boolean managerAlreadyMemberOfTheStaff(Organization organization, String membersUsername) {
		return ManagersDAO.getInstance(connection).isMemberOf(organization, membersUsername);
	}

	public boolean insertTeamMember(Team team, String newTeamMember) {
		Player p = PlayersDAO.getInstance(connection).getByUsername(newTeamMember);
		return PlayersDAO.getInstance(connection).insertIntoTeam(team, p);
	}

	public boolean removeTeamMember(Team team, String newTeamMember) {
		Player p = PlayersDAO.getInstance(connection).getByUsername(newTeamMember);
		return PlayersDAO.getInstance(connection).removeFromTeam(team, p);
	}

	public boolean playerIsMember(Team team, String newTeamMember) {
		List<Player> members = TeamsDAO.getInstance(connection).getTeamMembers(team.getTeamName());
		for(Player p : members)
			if(p.getUsername().compareTo(newTeamMember) == 0)
				return false;
		return true;
	}
}
