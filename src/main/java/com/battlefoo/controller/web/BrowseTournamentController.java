package com.battlefoo.controller.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.battlefoo.ServerPaths;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class BrowseTournamentController {
	@GetMapping("/BrowseTournament")
	public String getBrowseTournament(HttpServletRequest req) {
		List<Tournament> tournaments = Database.getInstance().getAllTournaments();
		for(Tournament t : tournaments) {
			if(t.getLogo().compareTo(ServerPaths.DEFAULT_ORGANIZATION_BANNER) != 0) {
				try {
					BufferedReader br =  new BufferedReader(new FileReader(t.getLogo()));
					t.setLogo(br.readLine());
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		req.getSession(true).setAttribute("allTournaments", tournaments);
		return "BrowseTournament";
	}
	
	
}
