package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Tournament;

@RestController
public class TournamentRestController {

	@PostMapping("/createTournament")
	public Response createTournament(HttpServletRequest req, @RequestBody Tournament tournament) {
		System.out.println(tournament.toString());
		System.out.println(tournament.getDate().getDate());
		return new Response();
	}
	
}
