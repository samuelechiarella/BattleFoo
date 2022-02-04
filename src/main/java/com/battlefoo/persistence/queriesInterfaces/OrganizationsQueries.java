package com.battlefoo.persistence.queriesInterfaces;

import java.util.List;

import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.model.entitiesObjects.Organization;

public interface OrganizationsQueries {
	public List<Organization> getAll();
	public Organization getByName(String name, long creatorId);
	public boolean exists(String name); 
	public List<Organization> getAllByCreatorId(long creatorId);
	public boolean insertMember(Manager creator, Organization organization, Manager newMember);
	public List<Organization> getAllByManagerId(long managerId);
	public Organization getById(Long orgId, Long creatorId);
	public boolean insertOrganization(Organization org);
	public List<Manager> getMembersByOrgName(String orgName);
	public boolean deleteById(Long orgId);
	public boolean editDescription(Organization currentOrganization, String description);
}
