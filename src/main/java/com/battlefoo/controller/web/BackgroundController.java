package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.battlefoo.model.CommonMethods;

@Controller
public class BackgroundController {
	@GetMapping("/index")
	public String getHomePage(HttpServletRequest req) {
		CommonMethods.updateTeamsAttribute(req, false);
		return "index";
	}
	
	@GetMapping("/organization")
	public String getOrganization(HttpServletRequest req) {
		if(req.getSession(true).getAttribute("loggedUser")==null)
			return "index";
		CommonMethods.updateTeamsAttribute(req, false);
		return "organization";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req){
		req.getSession(true).setAttribute("loggedUser", "");
		return "index";
	}
	
	@GetMapping("/teamPage")
	public String getTeamPage(HttpServletRequest req) {
		if(req.getSession(true).getAttribute("loggedUser")==null)
			return "index";
		CommonMethods.updateTeamsAttribute(req, false);
		return "teamPage";
	}
	
	// TEMP
	@GetMapping("/tournamentStructure")
	public String tournamentStructure() {
		return "tournamentStructure";
	}
}