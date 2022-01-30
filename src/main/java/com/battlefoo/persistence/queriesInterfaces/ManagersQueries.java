package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Manager;

public interface ManagersQueries {
	public List<Manager> getAll();
	public Manager getByUsername(String username);
	public boolean exists(String nickname);
	public Manager getById(Long managerId);
	public boolean insertManager(String username);
}
