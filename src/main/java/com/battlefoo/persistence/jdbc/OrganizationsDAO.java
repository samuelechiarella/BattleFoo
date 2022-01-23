package com.battlefoo.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.battlefoo.model.entitiesObjects.Manager;
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
	public Organization getByName(String name, long creatorId) {
		Organization org = null;
		String query = "select * from organizations where name=? and creator_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ps.setLong(2, creatorId);
			ResultSet res = ps.executeQuery();
			if(res.next())
				org = createOrganization(res);
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN ORGANIZATIONS DAO GET BY NAME");
		}
		return org;
	}

	public List<Manager> getMembersByOrganizationId(Long organizationId) {
		List<Manager> l = null;
		try {
			String query = "select * from organizations_members where organization_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, organizationId);
			ResultSet res = ps.executeQuery();
			l = new ArrayList<Manager>();
			while(res.next())
				l.add(ManagersDAO.getInstance(connection).getById(res.getLong("manager_id")));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public boolean exists(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Organization> getAllByCreatorId(long creatorId) {
		List<Organization> l = null;
		String query = "select * from organizations where creator_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, creatorId);
			ResultSet res = ps.executeQuery();
			l = new ArrayList<Organization>();
			while(res.next())
				l.add(createOrganization(res));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN ORGANIZATIONS DAO GET ALL BY CREATOR ID");
		}
		return l;
	}
	
	@Override
	public List<Organization> getAllByManagerId(long managerId) {
		List<Organization> l = null;
		String query = "select organizations.* from organizations_members full outer join organizations on organizations_members.organization_id = organizations.organization_id where manager_id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, managerId);
			ResultSet res = ps.executeQuery();
			l = new ArrayList<Organization>();
			while(res.next())
				l.add(createOrganization(res));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN ORGANIZATIONS DAO GET ALL BY CREATOR ID");
		}
		return l;
	}

	@Override
	public boolean insertMember(Manager creator, Organization organization, Manager newMember) {
		try {
			String query = "insert into organizations_members values(?,?);";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, organization.getOrganizationId());
			ps.setLong(2, newMember.getManagerId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR IN ORGANIZATIONS DAO INSERT MEMBER");
		}
		return false;
	}
	
	private Organization createOrganization(ResultSet res) throws SQLException {
		return new Organization(res.getLong("organization_id"),res.getString("name"),res.getString("description"),res.getLong("creator_id"));
	}
}
