package com.battlefoo.persistence.jdbc;

import java.util.List;

import com.battlefoo.model.Tournament;
import com.battlefoo.persistence.TournamentsQueries;

public class TournamentsDAO  implements TournamentsQueries{

	@Override
	public List<Tournament> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tournament getByTournamentName(String tournamentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String tournamentName) {
		// TODO Auto-generated method stub
		return false;
	}

}
