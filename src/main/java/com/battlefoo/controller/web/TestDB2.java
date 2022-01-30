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
	
	public Attendee[][][] getStructure(int numberOfAttendees, String winner, long tournamentId){
				
		int firstGroup = numberOfAttendees/2;
		
		int numberOfGroups = ((int) (Math.log(firstGroup) / Math.log(2))) + 1;
		
		Attendee[][][] attendees = null;
		
		attendees = new Attendee[numberOfGroups+1][][];
		
		//initialize structure
		for(int i = 0, numberOfMatches = numberOfAttendees/2; i < numberOfGroups; ++i, numberOfMatches/=2) {
			attendees[i] =new Attendee[numberOfMatches][2];
		}
		attendees[numberOfGroups] = new Attendee[1][1];

		for(int i = 0, numberOfMatches = numberOfAttendees/2; i < numberOfGroups; ++i, numberOfMatches/=2) {
			for(int j = 0; j < numberOfMatches; ++j) {
				String[][] teams = Database.getInstance().getTeamsByPhase(numberOfMatches, tournamentId);
				attendees[i][j][0] = new Attendee(teams[j][0],teams[j][0]);
				attendees[i][j][1] = new Attendee(teams[j][1],teams[j][1]);
			}
		}
		attendees[numberOfGroups][0][0] = new Attendee(winner, winner);
		
		return attendees;
	}
}
