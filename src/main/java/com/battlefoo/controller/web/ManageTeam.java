package com.battlefoo.controller.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.CommonMethods;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
@RestController
public class ManageTeam {
	@PostMapping("/createTeam")
	public Response createTeam(@RequestBody Team team, HttpServletRequest req){
		//	set attribute teamsList
		HttpSession session = req.getSession(true);
		Object obj = session.getAttribute("loggedUser");
		String user = "";
		if(obj instanceof String) {
			user = (String)obj;
		}
		Response response = createResponse(team,user);
		
		if(response.getResponseCode()==200)
			CommonMethods.updateTeamsAttribute(req, team);
		else
			CommonMethods.updateTeamsAttribute(req, null);
		return response;
	}
	
	private Response createResponse(Team team, String loggedUser) {
		Response res = new Response(Response.failure,"Storing failed");
		if(accepted(team.getTeamName())) {
			if(!Database.getInstance().teamExists(team.getTeamName())) {
				if(team.getLogo() != null) {
					// store logo into a text file named as below
					BufferedWriter writer;
					String imgFileName = new Date().getTime() + ".txt";
					try {
						writer = new BufferedWriter(new FileWriter(new File(ServerPaths.TEAMS_LOGOS_PATH, imgFileName)));
						writer.write(team.getLogo());
						writer.close();
					} catch (IOException e) {
						System.out.println("***********************************ManageTeam.java ERROR createTeam***********************************");
					}
					//*******************************************
					
					// set the team logo as the path to the text file instead
					team.setLogo(ServerPaths.TEAMS_LOGOS_PATH + imgFileName);
				}
				else {
					team.setLogo(ServerPaths.DEFAULT_LOGO);
				}
				long leaderId = -1;
				if(Database.getInstance().playerExists(loggedUser)) {
					leaderId = Database.getInstance().getPlayerByNickname(loggedUser).getPlayerId();
				}
				team.setLeaderId(leaderId);
				Database.getInstance().insertTeam(team);
				res.setResponseCode(Response.success);
				res.setResponseMessage("Team successfully stored");
			}
			else {
				res.setResponseCode(501);// team name already taken
				res.setResponseMessage("Team name already taken!");
			}
		}
		else {
			if(team.getTeamName().isEmpty()) {
				res.setResponseCode(502);
				res.setResponseMessage("Team name field is not filled!");
			}
			else {
				res.setResponseCode(503);
				res.setResponseMessage("Constraints not satisfied!");
			}
		}
		return res;
	}
	
	private boolean accepted(String s) {
		if(s.isEmpty() || s.contains(" ") || s.contains("-"))
			return false;
		Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
		Matcher m;
		m = p.matcher(s);
		if(!m.find())
			return false;
		return true;
	}
	
}
