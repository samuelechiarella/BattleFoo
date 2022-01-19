package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.battlefoo.model.Response;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class OrganizationRestController {
	@PostMapping("/addMember")
	public Response addMember(HttpServletRequest req, @RequestBody String membersUsername) {
		Response res = createAddMemberResponse((String)req.getSession(true).getAttribute("organization"), membersUsername);
		// in progress
		return res;
	}

	private Response createAddMemberResponse(String organizationName, String membersUsername) {
		Response res = new Response(Response.failure, "Adding member failed!");
		if(Database.getInstance().managerExists(membersUsername)) {
			if(Database.getInstance().insertTeamMember(organizationName, membersUsername)) {
				res.setResponseCode(200);
				res.setResponseMessage("Team member stored");
			}
			else {
				res.setResponseCode(502);
				res.setResponseMessage("Inserting Organization member failed!");
			}
		}
		else {
			res.setResponseCode(501);
			res.setResponseMessage("Selected Manager doesn't exists");
		}
		return res;
	}
}
