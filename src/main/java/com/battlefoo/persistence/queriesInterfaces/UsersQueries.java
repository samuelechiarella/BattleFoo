package com.battlefoo.persistence.queriesInterfaces;

import com.battlefoo.model.entitiesObjects.User;

public interface UsersQueries {
	public User getByEmail(String email);
	public boolean exists(String email);
	public boolean insertUser(User user);
}
