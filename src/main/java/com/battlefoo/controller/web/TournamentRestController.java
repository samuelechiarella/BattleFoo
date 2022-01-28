package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class TournamentRestController {

	@PostMapping("/createTournament")
	public Response createTournament(HttpServletRequest req, @RequestBody Tournament tournament) {
		Response response = createCreateTournamentResponse(req, tournament);
		return response;
	}

	private Response createCreateTournamentResponse(HttpServletRequest req, Tournament tournament) {
		Response res = new Response(Response.failure, "Storing tournament failed!");
		Manager creator = (Manager)req.getSession(true).getAttribute("loggedManager");
		if(creator != null)
			tournament.setManagerId(creator.getManagerId());
		else
			System.out.println("NULL CREATOR CREATE TOURNAMENT RESPONSE");
		if(Database.getInstance().insertTournament(tournament)) {
			res.setResponseCode(200);
			res.setResponseMessage("Tournament stored");
		}
		
		return res;
	}
	
}
