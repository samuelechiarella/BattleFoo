package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TournamentController {
	@GetMapping("/tournamentPage")
	public String getTournamentPage(HttpServletRequest req) {
		return "/tournamentStructure";
	}
}
