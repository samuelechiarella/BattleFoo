package com.battlefoo.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.entitiesObjects.Game;
import com.battlefoo.model.entitiesObjects.Organization;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.dbManagement.Database;

public class CommonMethods {
	public static void updateTeamsAttribute(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		List<Team> teams = null;
		
		// if a teams list exists yet and there is no team to add, stop 
		if(session.getAttribute("loggedUser")==null)
			return;
		
		// otherwise ask db for all the teams
		teams = Database.getInstance().getTeamsByPlayerUsername((String)session.getAttribute("loggedUser"));
		
		// the db stored the paths of all the teams logos, but we have wrote
		// the real base64 logos into many text files who follow THOSE paths, thus we
		// have to re-set them up, reading into THOSE files
		for(Team t : teams) {
			if(t.getLogo().compareTo(ServerPaths.DEFAULT_TEAM_LOGO)!=0) {
				try {
					BufferedReader br =  new BufferedReader(new FileReader(t.getLogo()));
					t.setLogo(br.readLine());
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		session.setAttribute("teamsList", teams);
	}
	
	public static void updateOrganizationsAttribute(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		List<Organization> organizations = null;
		
		// if an organizations list exists yet and there is no organization to add, stop 
		if(session.getAttribute("loggedManager") == null)
			return;
		
		organizations = Database.getInstance().getMyOrganizations((String)req.getSession(true).getAttribute("loggedUser"));
			
		session.setAttribute("organizationsList", organizations);
	}
	
	public static void updateGamesAttribute(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		List<Game> games = null;
		
		if(session.getAttribute("loggedManager") == null)
			return;
		
		games = Database.getInstance().getAllGames();
			
		session.setAttribute("gamesList", games);
	}
}
