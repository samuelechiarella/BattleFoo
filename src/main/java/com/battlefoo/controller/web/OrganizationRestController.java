package com.battlefoo.controller.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.model.entitiesObjects.Organization;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class OrganizationRestController {
	@PostMapping("/addMember")
	public Response addMember(HttpServletRequest req, @RequestBody String membersUsername) {
		String currentUser = (String)req.getSession(true).getAttribute("loggedUser");
		Organization currentOrganization = (Organization)req.getSession(true).getAttribute("organization");
		Response res = createAddMemberResponse(currentUser, currentOrganization, membersUsername.replace("\"", ""));
		if(res.getResponseCode()==200) {
			List<Manager> staff = (List<Manager>)req.getSession(true).getAttribute("staff");
			if(staff!=null) {
				staff.add(Database.getInstance().getManagerByUsername(membersUsername.replace("\"", "")));
				req.getSession(true).setAttribute("staff", staff);
			}
		}
		return res;
	}
	
	@PostMapping("/organizationPage")
	public Response getResponseOrganizationPage(HttpServletRequest req, @RequestBody Organization org) {
		return createOrganizationPageResponse(req,org);
	}

	private Response createOrganizationPageResponse(HttpServletRequest req, Organization org) {
		Response res = new Response(Response.failure, "Opening organization page failed!");
		List<Manager> staff = Database.getInstance().getOrganizationMembersById(org.getOrganizationId());
		req.getSession(true).setAttribute("organization", org);
		req.getSession(true).setAttribute("staff", staff);
		res.setResponseCode(200);
		res.setResponseMessage("Organization and staff added as attributes");
		return res;
	}

	private Response createAddMemberResponse(String creatorUsername, Organization organization, String membersUsername) {
		Response res = new Response(Response.failure, "Adding member failed!");
		if(Database.getInstance().managerExists(membersUsername)) {
			if(Database.getInstance().insertTeamMember(creatorUsername, organization.getOrganizationName(), membersUsername)) {
				res.setResponseCode(200);
				res.setResponseMessage("Team member stored");
				res.setData(Database.getInstance().getManagerByUsername(membersUsername));
			}
			else {
				res.setResponseCode(502);
				res.setResponseMessage("Inserting Organization member failed!");
			}
		}
		else {
			res.setResponseCode(501);
			res.setResponseMessage("Selected Manager doesn't exist");
		}
		return res;
	}
}
