package com.battlefoo.model.entitiesObjects;

public class Manager extends User {

	protected String managerId;
	
	public Manager(String nickname, String firstname, String lastname, String email, String password, String manager_id) {
		super(nickname, firstname, lastname, email, password);
		this.managerId = manager_id;
	}

	public String getManager_id() {
		return managerId;
	}

	public void setManager_id(String manager_id) {
		this.managerId = manager_id;
	}
}
