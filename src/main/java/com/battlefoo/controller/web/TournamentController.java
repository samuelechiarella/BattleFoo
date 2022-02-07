package com.battlefoo.controller.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.battlefoo.ServerPaths;
import com.battlefoo.model.entitiesObjects.Match;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class TournamentController {
	@GetMapping("/tournamentPage")
	public String getTournamentPage(HttpServletRequest req) {
		Tournament t = (Tournament)  req.getSession(true).getAttribute("tournament");
		if(t != null) {
			List<Team> tournamentAttendees = Database.getInstance().getTournamentAttendeesByTournamentName(t.getName());
			for(Team team : tournamentAttendees) {
				if(team.getLogo().compareTo(ServerPaths.DEFAULT_TEAM_LOGO) != 0) {
					try {
						BufferedReader br =  new BufferedReader(new FileReader(team.getLogo()));
						team.setLogo(br.readLine());
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
					team.setLogo(ServerPaths.DEFAULT_TEAM_LOGO);
			}
			
			List<Match> matches = Database.getInstance().getAllMatchesByTournamentId(t.getTournamentId());
			
			req.getSession(true).setAttribute("matches",matches);
			
			req.getSession(true).setAttribute("tournamentAttendees", tournamentAttendees);
			
			req.getSession(true).setAttribute("twitchChannel", t.getTwitchChannel());
		}
		return "/tournamentStructure";
	}
	
	@GetMapping("/visit{tournamentId}")
	public String getTournamentPage(@PathVariable("tournamentId") Long tournamentId, HttpServletRequest req) {
		Tournament t = Database.getInstance().getTournamentByTournamentId(tournamentId);
		if(t!=null) {
			// SET TOURNAMENT BANNER AND SPONSOR BANNER
			if(t.getLogo().compareTo(ServerPaths.DEFAULT_ORGANIZATION_BANNER) != 0) {
				try {
					BufferedReader br =  new BufferedReader(new FileReader(t.getLogo()));
					t.setLogo(br.readLine());
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				t.setLogo(ServerPaths.DEFAULT_ORGANIZATION_BANNER);
			
			//*******************************************************************************************************
			List<Team> tournamentAttendees = Database.getInstance().getTournamentAttendeesByTournamentId(tournamentId);
			
			for(Team team : tournamentAttendees) {
				if(team.getLogo().compareTo(ServerPaths.DEFAULT_TEAM_LOGO) != 0) {
					try {
						BufferedReader br =  new BufferedReader(new FileReader(team.getLogo()));
						team.setLogo(br.readLine());
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
					team.setLogo(ServerPaths.DEFAULT_TEAM_LOGO);
			}
			
			req.getSession(true).setAttribute("tournamentAttendees", tournamentAttendees);
			req.getSession(true).setAttribute("tournament", t);
			req.getSession(true).setAttribute("twitchChannel", t.getTwitchChannel());
			System.out.println(req.getSession(true).getAttribute("twitchChannel"));
		}
		return "tournamentStructure";
	}
}
