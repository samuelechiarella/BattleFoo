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
	public static void updateTeamsAttribute(HttpServletRequest req, Team team) {
		//	set attribute teamsList
		HttpSession session = req.getSession(true);
		List<Team> teams = null;
		
		if(session.getAttribute("teamsList") == null) {
			teams = Database.getInstance().getAllTeams();
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
		}
		
		else {
			// it supposed to be a list
			teams = (List<Team>) session.getAttribute("teamsList");
		}
		
		
		if(team!=null) {
			teams.add(team);
			if(team.getLogo().compareTo(ServerPaths.DEFAULT_LOGO)!=0) {
				try {
					BufferedReader br =  new BufferedReader(new FileReader(team.getLogo()));
					team.setLogo(br.readLine());
					br.close();
				} catch (IOException e) {
					System.out.println("ERROR IN MCOMMON METHODS CREATE TEAM IO EXCEPTION");
				}
			}
		}
		
		session.setAttribute("teamsList", teams);
		// 	***********************
	}
}
