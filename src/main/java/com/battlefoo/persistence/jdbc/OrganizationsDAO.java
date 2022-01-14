package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.util.List;

import com.battlefoo.model.entitiesObjects.Organization;
import com.battlefoo.persistence.queriesInterfaces.OrganizationsQueries;

public class OrganizationsDAO implements OrganizationsQueries {

	private static OrganizationsDAO instance = null;
	private Connection connection;
	
	private OrganizationsDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static OrganizationsDAO getInstance(Connection c) {
		if(instance == null)
			instance = new OrganizationsDAO(c);
		return instance;
	}
	
	@Override
	public List<Organization> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
