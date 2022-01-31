package com.battlefoo.controller.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.CommonMethods;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.Player;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class TeamRestController {
	
	@PostMapping("/editDescription")
	public Response editDescription(HttpServletRequest req, @RequestBody String description) {
		return createResponseEditDescription(req, description.replace("\"", ""));
	}
	
	private Response createResponseEditDescription(HttpServletRequest req, String description) {
		Response res = new Response(Response.failure, "Editing team description failed");
		Team t = (Team)req.getSession(true).getAttribute("team");
		if(Database.getInstance().editTeamDescription(t,description)) {
			res.setResponseCode(200);
			res.setResponseMessage("Description updated");
			t.setDescription(description);
			req.getSession(true).setAttribute("team", t);
		}
		return res;
	}

	@PostMapping("/createTeam")
	public Response createTeam(@RequestBody Team team, HttpServletRequest req){
		//	setting attribute teamsList
		HttpSession session = req.getSession(true);
		Object obj = session.getAttribute("loggedUser");
		String user = "";
		if(obj instanceof String) {
			user = (String)obj;
		}
		Response response = createCreateTeamResponse(req, team, user);
		
		if(response.getResponseCode()==200)
			CommonMethods.updateTeamsAttribute(req);
		else
			CommonMethods.updateTeamsAttribute(req);
		return response;
	}
	
	private Response createCreateTeamResponse(HttpServletRequest req, Team team, String loggedUser) {
		Response res = new Response(Response.failure,"Storing failed");
		team.setLeaderId(Database.getInstance().getPlayerByUsername(loggedUser).getPlayerId());
		if(usernameAccepted(team.getTeamName())) {
			if(!Database.getInstance().teamExists(team.getTeamName())) {
				if(team.getLogo() != null) {
					// storing logo into a text file named as below
					BufferedWriter writer;
					String imgFileName = new Date().getTime() + ".txt";
					try {
						writer = new BufferedWriter(new FileWriter(new File(ServerPaths.TEAMS_LOGOS_PATH, imgFileName)));
						writer.write(team.getLogo());
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//*******************************************
					
					req.getSession(true).setAttribute("team", team);
					
					// setting the team logo as the path to the text file instead
					team.setLogo(ServerPaths.TEAMS_LOGOS_PATH + imgFileName);
				}
				else {
					team.setLogo(ServerPaths.DEFAULT_TEAM_LOGO);
					req.getSession(true).setAttribute("team", team);
				}
				Database.getInstance().insertTeam(team);
				Database.getInstance().insertTeamMember(team, loggedUser);
				List<Player> membersList = Database.getInstance().getAllTeamMembers(team.getTeamName());
				req.getSession(true).setAttribute("teamMembersList", membersList);
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
	
	@PostMapping("/teamPage")
	public Response getResponseTeamPage(HttpServletRequest req, @RequestBody String teamName) {
		Response res = createTeamPageResponse(req,teamName.replace("\"", ""));
		return res;
	}
	
	@PostMapping("/addTeamMember")
	public Response getResponseAddTeamMember(HttpServletRequest req, @RequestBody String newTeamMember) {
		Response res = createAddTeamMemberResponse(req, newTeamMember.replace("\"", ""));
		return res;
	}
	
	@PostMapping("/removeTeamMember")
	public Response getResponseRemoveTeamMember(HttpServletRequest req, @RequestBody String newTeamMember) {
		Response res = createRemoveTeamMemberResponse(req, newTeamMember.replace("\"", ""));
		return res;
	}
	
	private Response createAddTeamMemberResponse(HttpServletRequest req, String newTeamMember) {
		Response res = new Response(Response.failure,"Adding team member failed!");
		if(Database.getInstance().playerExists(newTeamMember)) {
			if(Database.getInstance().playerIsMember((Team)req.getSession(true).getAttribute("team"), newTeamMember)){
				if(Database.getInstance().insertTeamMember((Team)req.getSession(true).getAttribute("team"), newTeamMember)) {
					res.setResponseCode(200);
					res.setResponseMessage("Team Member added");
					List<Player> members = (List<Player>) req.getSession(true).getAttribute("teamMembersList");
					members.add(Database.getInstance().getPlayerByUsername(newTeamMember));
					req.getSession(true).setAttribute("teamMembersList", members);
				}
				else {
					res.setResponseCode(503);
					res.setResponseMessage("Storing team member failed!");
				}
			}
			else {
				res.setResponseCode(502);
				res.setResponseMessage("It is already Member!");
			}
		}
		else {
			res.setResponseCode(501);
			res.setResponseMessage("Username doesn't exist");
		}
		return res;
	}
	
	private Response createRemoveTeamMemberResponse(HttpServletRequest req, String newTeamMember) {
		Response res = new Response(Response.failure,"Removing team member failed!");
		if(Database.getInstance().playerExists(newTeamMember)) {
			if(Database.getInstance().removeTeamMember((Team)req.getSession(true).getAttribute("team"), newTeamMember)) {
				res.setResponseCode(200);
				res.setResponseMessage("Team Member removed");
				List<Player> members = (List<Player>) req.getSession(true).getAttribute("teamMembersList");
				members.remove(Database.getInstance().getPlayerByUsername(newTeamMember));
				req.getSession(true).setAttribute("teamMembersList", members);
			}
			else {
				res.setResponseCode(502);
				res.setResponseMessage("Removing team member failed!");
			}
		}
		else {
			res.setResponseCode(501);
			res.setResponseMessage("Username doesn't exist");
		}
		return res;
	}

	
	
	private Response createTeamPageResponse(HttpServletRequest req, String teamName) {
		Response res = new Response(Response.failure,"Creating Team Page failed!");
		Team team = Database.getInstance().getTeamByTeamName(teamName);
		if(team != null) {
			List<Player> teamMembersList = Database.getInstance().getAllTeamMembers(teamName);
			if(teamMembersList != null) {
				if(team.getLogo().compareTo(ServerPaths.DEFAULT_TEAM_LOGO)!=0) {
					try {
						BufferedReader br =  new BufferedReader(new FileReader(team.getLogo()));
						team.setLogo(br.readLine());
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				for(Player p : teamMembersList) {
					if(p.getProfilePicture().compareTo(ServerPaths.DEFAULT_PROFILE_PICTURE)!=0) {
						try {
							BufferedReader br =  new BufferedReader(new FileReader(p.getProfilePicture()));
							p.setProfilePicture(br.readLine());
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				req.getSession(true).setAttribute("teamMembersList", teamMembersList);
				req.getSession(true).setAttribute("team", team);
				res.setResponseCode(200);
				res.setResponseMessage("Team and team members were added to the attributes");
			}
			else {
				res.setResponseCode(501);
				res.setResponseMessage("The team members list is null!");
			}
		}
		else {
			res.setResponseCode(502);
			res.setResponseMessage("The team is null!");
		}
		return res;
	}
	
	private boolean usernameAccepted(String s) {
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
