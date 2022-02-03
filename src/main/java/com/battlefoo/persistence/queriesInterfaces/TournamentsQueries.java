package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.model.entitiesObjects.Tournament;

public interface TournamentsQueries {
	public List<Tournament> getAll();
	public Tournament getByTournamentName(String tournamentName);
	public boolean exists(String tournamentName);
	public Tournament getByCreatorUsername(String username);
	public boolean insertTournament(Tournament tournament, Long organizationId);
	public List<Tournament> getByOrganizationId(Long organizationId);
	public Tournament getById(Long tournamentId);
	public List<String> getAttendeesById(Long tounamentId);
	public boolean insertTeam(Team team, Tournament tournament);
	public boolean deleteTournamentById(Long tournamentId);
	public boolean deleteTeamFromTournament(Team team, Tournament tournament);
}
