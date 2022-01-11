package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Organization;

public interface OrganizationsQueries {
	public List<Organization> getAll();
	public Organization getByName(String name);
	public boolean exists(String name); 
}
