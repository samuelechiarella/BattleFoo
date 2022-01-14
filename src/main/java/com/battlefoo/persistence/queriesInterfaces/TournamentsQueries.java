package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Tournament;

public interface TournamentsQueries {
	public List<Tournament> getAll();
	public Tournament getByTournamentName(String tournamentName);
	public boolean exists(String tournamentName);
}
