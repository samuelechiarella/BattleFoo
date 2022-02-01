package com.battlefoo.controller.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.battlefoo.ServerPaths;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class TournamentController {
	@GetMapping("/tournamentPage")
	public String getTournamentPage(HttpServletRequest req) {
		Tournament t = (Tournament)  req.getSession(true).getAttribute("tournament");
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
		req.getSession(true).setAttribute("tournamentAttendees", tournamentAttendees);
		return "/tournamentStructure";
	}
}
