package com.battlefoo.persistence;

import java.util.List;

import com.battlefoo.model.Tournament;

public interface TournamentsQueries {
	public List<Tournament> getAll();
	public Tournament getByTournamentName(String tournamentName);
	public boolean exists(String tournamentName);
}
