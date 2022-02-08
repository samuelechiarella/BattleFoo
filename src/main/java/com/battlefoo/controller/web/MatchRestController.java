package com.battlefoo.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Manager;
import com.battlefoo.model.entitiesObjects.Match;
import com.battlefoo.model.entitiesObjects.Player;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class MatchRestController {

	@PostMapping("/matchPage")
	public Response getMatchPage(HttpServletRequest req, @RequestBody Long matchId) {
		Response res = new Response(Response.failure, "Redirecting to match page failed");
		Tournament t = (Tournament) req.getSession(true).getAttribute("tournament");
		if(t != null) {
			Match match = Database.getInstance().getMatchById(matchId);
			if(match != null) {
				List<Manager> matchStaff = Database.getInstance().getMatchStaff(t.getTournamentId());
				if(matchStaff != null) {
					List<Player> matchAttendees = Database.getInstance().getMatchLongAttendeesByMatchId(matchId);
					res.setResponseCode(200);
					res.setResponseMessage("Redirect to Match page");
					req.getSession(true).setAttribute("match",match);
					req.getSession(true).setAttribute("matchStaff",matchStaff);
					req.getSession(true).setAttribute("matchAttendees", matchAttendees);
				}
				else
					res.setResponseMessage("Match staff == null");
			}
			else
				res.setResponseMessage("Match = null");
		}
		return res;
	}
	
	@PostMapping("/sendMessage")
	public Response getSendMessageResponse(HttpServletRequest req, @RequestBody String message) {
		return createSendMessageResponse(req,message.replace("\"", ""));
	}

	private Response createSendMessageResponse(HttpServletRequest req, String message) {
		Match currentMatch = (Match) req.getSession(true).getAttribute("match");
		String chatHistory = Database.getInstance().getChatHistory(currentMatch.getMatchId());
		if(chatHistory == null)
			chatHistory = "";
		chatHistory+=(String)req.getSession(true).getAttribute("loggedUser") + ": " + message + "\n";
		Database.getInstance().setChatHistory(chatHistory,currentMatch.getMatchId());
		Response res = new Response(Response.success, "Message sent");
		return res;
	}
	
	@GetMapping("/refreshChatHistory")
	public Response getChatHistory(HttpServletRequest req) {
		Match currentMatch = (Match) req.getSession(true).getAttribute("match");
		String chatHistory = Database.getInstance().getChatHistory(currentMatch.getMatchId());
		if(chatHistory == null)
			chatHistory = "";
		chatHistory+=(String)req.getSession(true).getAttribute("loggedUser");
		Response res = new Response(Response.success, "Chat refreshed",chatHistory);
		return res;
	}
}
