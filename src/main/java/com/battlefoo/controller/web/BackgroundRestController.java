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
import com.battlefoo.model.entitiesObjects.User;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class BackgroundRestController {
	@PostMapping("/login")
	public Response login(HttpServletRequest req, @RequestBody User user){
		Response response = createCreateLoginResponse(user);
		
		if(response.getResponseCode()==200)
			req.getSession(true).setAttribute("loggedUser", user.getUsername());
		
		return response;
	}
	
	@PostMapping("/signup")
	public Response signup(HttpServletRequest req, @RequestBody User user){
		user.setProfilePicture(ServerPaths.DEFAULT_PROFILE_PICTURE);
		Response response = createSignupResponse(user);
		if(response.getResponseCode()==200)
			req.getSession(true).setAttribute("loggedUser", user.getUsername());
		
		return response;
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
		Response response = createTeamResponse(team,user);
		
		if(response.getResponseCode()==200)
			CommonMethods.updateTeamsAttribute(req, true);
		else
			CommonMethods.updateTeamsAttribute(req, false);
		return response;
	}
	
	@PostMapping("/teamPage")
	public Response getResponseTeamPage(HttpServletRequest req, @RequestBody String teamName) {
		Response res = createTeamPageResponse(req,teamName.replace("\"", ""));
		return res;
	}
	
	private Response createTeamPageResponse(HttpServletRequest req, String teamName) {
		Response res = new Response(Response.failure,"Creating Team Page failed!");
		Team team = Database.getInstance().getTeamByTeamName(teamName);
		if(team != null) {
			List<Player> teamMembersList = Database.getInstance().getAllTeamMembers(teamName);
			if(teamMembersList != null) {
				if(team.getLogo().compareTo(ServerPaths.DEFAULT_LOGO)!=0) {
					try {
						BufferedReader br =  new BufferedReader(new FileReader(team.getLogo()));
						team.setLogo(br.readLine());
						br.close();
					} catch (IOException e) {
						System.out.println("ERROR IN COMMON METHODS CREATE TEAM IO EXCEPTION");
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

	private Response createCreateLoginResponse(User user) {
		Response res = new Response(Response.failure,"Storing failed");
		if(Database.getInstance().allowLogIn(user.getUsername(), user.getPassword())) {
			res.setResponseCode(Response.success);
			res.setResponseMessage("Logged");
		}
		else
			res.setResponseMessage("Log in failed");
		return res;
	}
	
	private Response createSignupResponse(User user) {
		Response res = new Response(Response.failure,"Storing failed");
		if(!emailAccepted(user.getEmail())) {
			res.setResponseCode(501);
			res.setResponseMessage("Invalid email");
			return res;
		}
		if(!Database.getInstance().playerExists(user.getEmail())) {
			if(!Database.getInstance().playerExists(user.getUsername())) {
				Database.getInstance().insertUser(user);
				res.setResponseCode(200);
				res.setResponseMessage("Users added");
			}
			else {
				res.setResponseCode(503);
				res.setResponseMessage("Username already exists!");
			}
		}
		else {
			res.setResponseCode(502);
			res.setResponseMessage("Email already exists!");
		}
		return res;
	}
	
	private boolean emailAccepted(String email) {
		return true;
	}

	private Response createTeamResponse(Team team, String loggedUser) {
		Response res = new Response(Response.failure,"Storing failed");
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
						System.out.println("***********************************ManageTeam.java ERROR createTeam***********************************");
					}
					//*******************************************
					
					// setting the team logo as the path to the text file instead
					team.setLogo(ServerPaths.TEAMS_LOGOS_PATH + imgFileName);
				}
				else {
					team.setLogo(ServerPaths.DEFAULT_LOGO);
				}
				team.setLeaderId(Database.getInstance().getPlayerByUsername(loggedUser).getPlayerId());
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
