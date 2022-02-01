package com.battlefoo.controller.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.CommonMethods;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.model.entitiesObjects.Organization;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class OrganizationRestController {
	@PostMapping("/createOrganization")
	public Response createOrganization(HttpServletRequest req, @RequestBody Organization org) {
		return createCreateOrganizationResponse(req, org);
	}

	@PostMapping("/addMember")
	public Response addMember(HttpServletRequest req, @RequestBody String membersUsername) {
		String currentUser = (String)req.getSession(true).getAttribute("loggedUser");
		Organization currentOrganization = (Organization)req.getSession(true).getAttribute("organization");
		
		Response res = createAddMemberResponse(currentUser, currentOrganization, membersUsername.replace("\"", ""));
		if(res.getResponseCode()==200) {
			List<Manager> staff = (List<Manager>)req.getSession(true).getAttribute("staff");
			if(staff!=null) {
				staff.add(Database.getInstance().getManagerByUsername(membersUsername.replace("\"", "")));
				for(Manager m : staff) {
					if(m.getProfilePicture().compareTo(ServerPaths.DEFAULT_PROFILE_PICTURE)!=0) {
						try {
							BufferedReader br =  new BufferedReader(new FileReader(m.getProfilePicture()));
							m.setProfilePicture(br.readLine());
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				req.getSession(true).setAttribute("staff", staff);
			}
		}
		return res;
	}
	
	@PostMapping("/organizationPage")
	public Response getResponseOrganizationPage(HttpServletRequest req, @RequestBody Long[] orgIdAndCreatorId) {
		return createOrganizationPageResponse(req,orgIdAndCreatorId);
	}
	
	private Response createCreateOrganizationResponse(HttpServletRequest req, Organization org) {
		Response res = new Response(Response.failure, "Storing organization failed!");
		Manager creator = (Manager)req.getSession(true).getAttribute("loggedManager");
		org.setCreatorId(creator.getManagerId());
		if(org.getBanner() != null) {
			BufferedWriter writer;
			String bannerFileName = new Date().getTime() + ".txt";
			try {
				writer = new BufferedWriter(new FileWriter(new File(ServerPaths.ORGANIZATIONS_BANNERS_PATH, bannerFileName)));
				writer.write(org.getBanner());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			req.getSession(true).setAttribute("organization", org);
			org.setBanner(ServerPaths.ORGANIZATIONS_BANNERS_PATH + bannerFileName);
		}
		else {
			org.setBanner(ServerPaths.DEFAULT_ORGANIZATION_BANNER);
			req.getSession(true).setAttribute("organization", org);
		}
		if(Database.getInstance().insertOrganization(org)) {
			Database.getInstance().insertOrganizationMember(creator.getUsername(), org.getOrganizationName(), creator.getUsername());
			List<Manager> staff = Database.getInstance().getOrganizationMembersByOrgName(org.getOrganizationName());
			req.getSession(true).setAttribute("staff", staff);
			
			org = Database.getInstance().getOrganizationByOrganizationName(org.getOrganizationName(), org.getCreatorId());
			
			req.getSession(true).setAttribute("organization", org);
			CommonMethods.updateOrganizationsAttribute(req);
			res.setResponseCode(200);
			res.setResponseMessage("Organization stored");
		}
		return res;
	}

	private Response createOrganizationPageResponse(HttpServletRequest req, Long[] orgIdAndCreatorId) {
		Response res = new Response(Response.failure, "Opening organization page failed!");
		Organization org = Database.getInstance().getOrganizationById(orgIdAndCreatorId[0],orgIdAndCreatorId[1]);
		List<Manager> staff = Database.getInstance().getOrganizationMembersById(org.getOrganizationId());
		List<Tournament> tournaments = Database.getInstance().getTournamentsByOrganizationId(org.getOrganizationId());
		for(Manager m : staff) {
			if(m.getProfilePicture().compareTo(ServerPaths.DEFAULT_PROFILE_PICTURE) != 0) {
				try {
					BufferedReader br =  new BufferedReader(new FileReader(m.getProfilePicture()));
					m.setProfilePicture(br.readLine());
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		req.getSession(true).setAttribute("tournamentsList", tournaments);
		req.getSession(true).setAttribute("organization", org);
		req.getSession(true).setAttribute("staff", staff);
		res.setResponseCode(200);
		res.setResponseMessage("Organization and staff added as attributes");
		return res;
	}

	private Response createAddMemberResponse(String creatorUsername, Organization organization, String membersUsername) {
		Response res = new Response(Response.failure, "Adding member failed!");
		if(Database.getInstance().managerExists(membersUsername)) {
			if(!Database.getInstance().managerAlreadyMemberOfTheStaff(organization, membersUsername)) {
				if(Database.getInstance().insertOrganizationMember(creatorUsername, organization.getOrganizationName(), membersUsername)) {
					res.setResponseCode(200);
					res.setResponseMessage("Team member stored");
					res.setData(Database.getInstance().getManagerByUsername(membersUsername));
				}
				else {
					res.setResponseCode(503);
					res.setResponseMessage("Inserting Organization member failed!");
				}
			}
			else {
				res.setResponseCode(502);
				res.setResponseMessage("Already member of the staff");
			}
		}
		else {
			res.setResponseCode(501);
			res.setResponseMessage("Selected Manager doesn't exist");
		}
		return res;
	}
}