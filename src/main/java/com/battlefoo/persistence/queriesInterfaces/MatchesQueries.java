package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Match;

public interface MatchesQueries {
	public List<Match> getAll();
	public List<Match> getAllByTournamentId(Long tounamentId);
	public Match getMatch(String team1, String team2, Long tournamentId);
	public String[][] getTeamsByPhase(int numeroPartite, Long tournamentId);
	public String getChatByMatchId(Long matchId);
}
