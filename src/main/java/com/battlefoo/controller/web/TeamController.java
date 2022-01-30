package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.battlefoo.model.CommonMethods;

@Controller
public class TeamController {
	
	@GetMapping("/teamPage")
	public String getTeamPage(HttpServletRequest req) {
		if(req.getSession(true).getAttribute("loggedUser")==null)
			return "index";
		CommonMethods.updateTeamsAttribute(req, false);
		CommonMethods.updateOrganizationsAttribute(req, false);
		return "teamPage";
	}
}
