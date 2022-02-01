package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class BrowseTournamentController {
	@GetMapping("/BrowseTournament")
	public String getBrowseTournament(HttpServletRequest req) {
		req.getSession(true).setAttribute("allTournaments", Database.getInstance().getAllTournaments());
		return "BrowseTournament";
	}
}
