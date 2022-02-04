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
	
	@PostMapping("/removeMember")
	public Response removeMember(HttpServletRequest req, @RequestBody String membersUsername ) {
		Response res = new Response(Response.failure,"Removing members fail");
		membersUsername = membersUsername.replace("\"", "");
		if(Database.getInstance().managerExists(membersUsername)) {
			List<Manager> staff = (List<Manager>) req.getSession(true).getAttribute("staff");
			boolean membroTrovato = false;
			for(Manager m : staff) {
				if(m.getUsername().compareTo(membersUsername) == 0) {
					membroTrovato = true;
				}
			}
			if(membroTrovato) {
				Organization org = (Organization) req.getSession(true).getAttribute("organization");
				if (Database.getInstance().removeStaffMember(org, membersUsername)) {
					Manager temp = null;
					for(Manager m : staff)
					{
						if( m.getUsername().compareTo(membersUsername) == 0) {
							temp = m;
							break;
						}
					}
					staff.remove(temp);
					req.getSession(true).setAttribute("staff", staff);
					res.setResponseCode(200);
					res.setResponseMessage("Staff member removed");
				}
			}
				
		} else res.setResponseMessage("manager doesn't exist");
		
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
		String base64Image = "";
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
			base64Image = org.getBanner();
			org.setBanner(ServerPaths.ORGANIZATIONS_BANNERS_PATH + bannerFileName);
		}
		else {
			org.setBanner(ServerPaths.DEFAULT_ORGANIZATION_BANNER);
		}
		if(Database.getInstance().insertOrganization(org)) {
			Database.getInstance().insertOrganizationMember(creator.getUsername(), org.getOrganizationName(), creator.getUsername());
			List<Manager> staff = Database.getInstance().getOrganizationMembersByOrgName(org.getOrganizationName());
			req.getSession(true).setAttribute("staff", staff);
			
			org.setOrganizationId(Database.getInstance().getOrganizationByOrganizationName(org.getOrganizationName(), org.getCreatorId()).getOrganizationId());
			if(!base64Image.isEmpty()) {
				org.setBanner(base64Image);
			}
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
		
		if(org.getBanner().compareTo(ServerPaths.DEFAULT_ORGANIZATION_BANNER) != 0) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(new File(org.getBanner())));
				org.setBanner(reader.readLine());
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
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
	
	@PostMapping("/deleteTournament")
	public Response deleteTournament(HttpServletRequest req, @RequestBody Long tournamentId) {
		Response res = new Response(Response.failure, "Deleting touranment failed!");
		if(Database.getInstance().deleteTournamentById(tournamentId)) {
			List<Tournament> tournaments = (List<Tournament>) req.getSession(true).getAttribute("tournamentsList");
			Tournament deletedTournament = null;
			for(Tournament t : tournaments)
				if(t.getTournamentId() == tournamentId) {
					deletedTournament = t;
					break;
				}
			if(deletedTournament!=null)
				tournaments.remove(deletedTournament);
			req.getSession(true).setAttribute("tournamentsList", tournaments);
			res.setResponseCode(200);
			res.setResponseMessage("Tournament deleted");
		}
		return res;
	}
	
	@PostMapping("/deleteOrganization")
	public Response deleteOrganization(HttpServletRequest req, @RequestBody Long orgId) {
		Response res = new Response(Response.failure, "Deleting organization failed!");
		List<Tournament> tournaments = Database.getInstance().getTournamentsByOrganizationId(orgId);
		for(Tournament t : tournaments)
			Database.getInstance().deleteTournamentById(t.getTournamentId());
		if(Database.getInstance().deleteOrganizationById(orgId)) {
			res.setResponseCode(200);
			res.setResponseMessage("Organization deleted");
		}
		return res;
	}
	
	@PostMapping("/editOrganizationDescription")
	public Response editOrganizationDescription(HttpServletRequest req, @RequestBody String orgDescr) {
		Response res = new Response(Response.failure, "Updating description failed");
		orgDescr = orgDescr.replace("\"", "");
		orgDescr = orgDescr.replace("\\n", "\n");
		Organization currentOrganization = (Organization) req.getSession(true).getAttribute("organization");
		if(Database.getInstance().editOrganizationDescription(currentOrganization, orgDescr)) {
			res.setResponseCode(200);
			res.setResponseMessage("Organization description updated");
			currentOrganization.setDescription(orgDescr);
			req.getSession(true).setAttribute("organization", currentOrganization);
		}
		return res;
	}
}