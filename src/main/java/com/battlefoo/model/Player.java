package com.battlefoo.model;

public class Player extends User {

	private String playerId;
	
	public Player(String nickname, String firstname, String lastname, String email, String password, String player_id) {
		super(nickname, firstname, lastname, email, password);
		this.playerId = player_id;
	}

	public String getPlayer_id() {
		return playerId;
	}

	public void setPlayer_id(String player_id) {
		this.playerId = player_id;
	}
}
