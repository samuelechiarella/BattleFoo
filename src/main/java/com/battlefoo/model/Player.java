package com.battlefoo.model;

public class Player extends User {

	private String player_id;
	
	public Player(String nickname, String firstname, String lastname, String email, String password, String player_id) {
		super(nickname, firstname, lastname, email, password);
		this.player_id = player_id;
	}

	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}
}
