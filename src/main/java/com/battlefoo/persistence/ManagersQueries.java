package com.battlefoo.persistence;

import java.util.List;

import com.battlefoo.model.Manager;

public interface ManagersQueries {
	public List<Manager> getAll();
	public Manager getByNickname(String nickname);
	public boolean exists(String nickname); 
}
