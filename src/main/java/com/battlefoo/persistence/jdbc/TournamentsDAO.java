package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.queriesInterfaces.TournamentsQueries;

public class TournamentsDAO  implements TournamentsQueries{

	private static TournamentsDAO instance = null;
	private Connection connection;
	
	private TournamentsDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static TournamentsDAO getInstance(Connection c) {
		if(instance == null)
			instance = new TournamentsDAO(c);
		return instance;
	}
	
	@Override
	public List<Tournament> getAll() {
		List<Tournament> tournaments = null;
		String query = "select * from tournaments;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			tournaments = new ArrayList<Tournament>();
			while(res.next()) {
				tournaments.add(createTournament(res));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return tournaments;
	}

	@Override
	public Tournament getByTournamentName(String tournamentName) {
		Tournament t = null;
		String query = "select * from tournaments where name=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, tournamentName);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				t = createTournament(res);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public boolean exists(String tournamentName) {
		String query = "select * from tournaments where name=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, tournamentName);
			ResultSet res = ps.executeQuery();
			if(res.next())
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Tournament getByCreatorUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertTournament(Tournament tournament, Long organizationId) {
		String query = "insert into tournaments(name,date,description,rules,schedule,game_name,sponsor,logo,manager_id,num_of_attendees)"
				+ "		values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, tournament.getName());
			ps.setString(2, tournament.getDate().getDate());
			ps.setString(3, tournament.getDescription());
			ps.setString(4, tournament.getRules());
			ps.setString(5, tournament.getSchedule());
			ps.setString(6, tournament.getGameName());
			ps.setString(7, tournament.getSponsor());
			ps.setString(8, tournament.getLogo());
			ps.setLong(9, tournament.getManagerId());
			ps.setInt(10, tournament.getNumOfAttendees());
			ps.execute();
			
			insertInTournamentsStaff(getByTournamentName(tournament.getName()), organizationId);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void insertInTournamentsStaff(Tournament tournament, Long organizationId) {
		String query = "insert into tournaments_staff values(?,?);";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tournament.getTournamentId());
			ps.setLong(2, organizationId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Tournament> getByOrganizationId(Long organizationId) {
		List<Tournament> tournaments = null;
		String query = "select tournaments.* from tournaments full outer join tournaments_staff on tournaments.tournament_id = tournaments_staff.tournament_id where tournaments_staff.organization_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, organizationId);
			ResultSet res = ps.executeQuery();
			tournaments = new ArrayList<Tournament>();
			while(res.next()) {
				tournaments.add(createTournament(res));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return tournaments;
	}
	
	@Override
	public Tournament getById(Long tournamentId) {
		Tournament t = null;
		String query = "select * from tournaments where tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tournamentId);
			ResultSet res = ps.executeQuery();
			if(res.next())
				t = createTournament(res);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	@Override
	public List<String> getAttendeesById(Long tounamentId) {
		List<String> attendees = null;
		String query = "select team_name from tournaments_attendees where tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tounamentId);
			ResultSet res = ps.executeQuery();
			attendees = new ArrayList<String>();
			while(res.next()) {
				attendees.add(res.getString("team_name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return attendees;
	}
	
	public List<String> getAttendeesByName(String name) {
		List<String> attendees = null;
		String query = "select tournaments_attendees.team_name from tournaments_attendees,tournaments where"
					+ " tournaments.tournament_id = tournaments_attendees.tournament_id and tournaments.name=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ResultSet res = ps.executeQuery();
			attendees = new ArrayList<String>();
			while(res.next()) {
				attendees.add(res.getString("team_name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return attendees;
	}

	@Override
	public boolean insertTeam(Team team, Tournament tournament) {
		String query = "insert into tournaments_attendees values(?,?);";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, team.getTeamName());
			ps.setLong(2, tournament.getTournamentId());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Tournament> getListByFilter(String filter) {
		List<Tournament> tournamentFilter = null;
		String query = "select * from tournaments where name like ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, filter);
			ResultSet res = ps.executeQuery();
			tournamentFilter = new ArrayList<Tournament>();
			while(res.next())
				tournamentFilter.add(createTournament(res));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tournamentFilter;
	}

	@Override
	public boolean deleteTournamentById(Long tournamentId) {
		String query = "delete from tournaments where tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tournamentId);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTeamFromTournament(Team team, Tournament tournament) {
		String query = "delete from tournaments_attendees where team_name=? and tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, team.getTeamName());
			ps.setLong(2, tournament.getTournamentId());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean setTwitchChannel(Tournament t, String twitchChannel) {
		String query = "update tournaments set twitch_channel=? where tournament_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, twitchChannel);
			ps.setLong(2, t.getTournamentId());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Tournament createTournament(ResultSet res) throws SQLException {
		Tournament t = new Tournament(res.getString(1),res.getString(2),res.getString(3),
				res.getString(4),res.getString(5),res.getString(6),res.getString(7),
				res.getString(8),res.getLong(9),res.getLong(10),res.getInt(11));
		t.setTwitchChannel(res.getString(12));
		return t;
	}

	public List<Long> getTournamentStaff(Long tournamentId) {
		List<Long> managersId = null;
		String query = "select organizations_members.manager_id "
				+ "from tournaments,tournaments_staff,organizations,organizations_members "
				+ "where tournaments.tournament_id = tournaments_staff.tournament_id "
				+ "and tournaments_staff.organization_id = organizations.organization_id "
				+ "and organizations.organization_id = organizations_members.organization_id "
				+ "and tournaments.tournament_id = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tournamentId);
			ResultSet res = ps.executeQuery();
			managersId = new ArrayList<Long>();
			while(res.next())
				managersId.add(res.getLong(1));
		}
		catch(SQLException e) {
			
		}
		return managersId;
	}
}
