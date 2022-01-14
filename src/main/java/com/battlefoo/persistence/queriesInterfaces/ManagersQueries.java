package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Manager;

public interface ManagersQueries {
	public List<Manager> getAll();
	public Manager getByNickname(String nickname);
	public boolean exists(String nickname); 
}
