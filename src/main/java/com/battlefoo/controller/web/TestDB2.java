package com.battlefoo.controller.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.model.Attendee;
import com.battlefoo.persistence.dbManagement.Database;


@RestController
public class TestDB2 {
	@GetMapping("/testDB2")
	public Attendee[][][] getObj() {
		//List<Match> matches = Database.getInstance().getAllMatchesByTournamentId((long)1);
		return getStructure(8,null,1);
	}
	
	public Attendee[][][] getStructure(int numeroPartecipanti, String vincitore, long tournamentId){
				
		int gironeIniziale = numeroPartecipanti/2;
		
		int numeroGironi = ((int) (Math.log(gironeIniziale) / Math.log(2))) + 1;
		
		Attendee[][][] attendees = null;
		
		attendees = new Attendee[numeroGironi+1][][];
		attendees[numeroGironi] = new Attendee[1][1];
		
		if(vincitore != null) {
			attendees[numeroGironi][0][0] = new Attendee(vincitore, vincitore);
		}
		else
			attendees[numeroGironi][0][0] = new Attendee(vincitore, "");
		
		//initialize structure
		for(int i = 0, numeroPartite = numeroPartecipanti/2; i < numeroGironi; ++i, numeroPartite/=2) {
			attendees[i] =new Attendee[numeroPartite][2];
		}

		for(int i = 0, numeroPartite = numeroPartecipanti/2; i < numeroGironi; ++i, numeroPartite/=2) {
			for(int j = 0; j < numeroPartite; ++j) {
				String[][] teams = Database.getInstance().getTeamsByPhase(numeroPartite, tournamentId);
				attendees[i][j][0] = new Attendee(teams[j][0],teams[j][0]);
				attendees[i][j][1] = new Attendee(teams[j][1],teams[j][1]);
			}
		}
		
		return attendees;
	}
}
