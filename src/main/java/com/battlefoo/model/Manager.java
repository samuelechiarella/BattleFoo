package com.battlefoo.model;

public class Manager extends User {

	protected String manager_id;
	
	public Manager(String nickname, String firstname, String lastname, String email, String password, String manager_id) {
		super(nickname, firstname, lastname, email, password);
		this.manager_id = manager_id;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
}
