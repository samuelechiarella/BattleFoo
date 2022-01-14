package com.battlefoo.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class BackgroundController {
	@GetMapping("/index")
	public String getHomePage(HttpServletRequest req) {
		//	set attribute teamsList
		HttpSession session = req.getSession(true);
		List<Team> teams = Database.getInstance().getAllTeams();
		session.setAttribute("teamsList", teams);
		// 	***********************
		return "index";
	}
	
	@GetMapping("/organization")
	public String getOrganization(HttpServletRequest req) {
		//	set attribute teamsList
		HttpSession session = req.getSession(true);
		List<Team> teams = Database.getInstance().getAllTeams();
		session.setAttribute("teamsList", teams);
		// 	***********************
		
		return "organization";
	}
}