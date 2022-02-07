package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Player;
import com.battlefoo.model.entitiesObjects.Team;

public interface PlayersQueries {
	public List<Player> getAll();
	public Player getByUsername(String username);
	public boolean exists(String nickname);
	public boolean insertIntoTeam(Team team, Player newTeamMember);
	public boolean removeFromTeam(Team team, Player p);
}
