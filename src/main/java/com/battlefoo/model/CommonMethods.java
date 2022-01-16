package com.battlefoo.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.dbManagement.Database;

public class CommonMethods {
	public static void updateTeamsAttribute(HttpServletRequest req, boolean teamAdded) {
		HttpSession session = req.getSession(true);
		List<Team> teams = null;
		
		// if a teams list exists yet and there is no team to add, stop 
		if(session.getAttribute("teamsList") != null && !teamAdded)
			return;
		
		// otherwise ask db for all the teams
		teams = Database.getInstance().getAllTeams();
		
		// the db stored the paths of all the teams logos, but we have wrote
		// the real base64 logos into many text files who follow THOSE paths, thus we
		// have to re-set them up, reading into THOSE files
		for(Team t : teams) {
			if(t.getLogo().compareTo(ServerPaths.DEFAULT_LOGO)!=0) {
				try {
					BufferedReader br =  new BufferedReader(new FileReader(t.getLogo()));
					t.setLogo(br.readLine());
					br.close();
				} catch (IOException e) {
					System.out.println("ERROR IN COMMON METHODS CREATE TEAM IO EXCEPTION");
				}
			}
		}
		session.setAttribute("teamsList", teams);
	}
}
