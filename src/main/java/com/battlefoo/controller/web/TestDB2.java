package com.battlefoo.controller.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battlefoo.model.Attendee;


@RestController
public class TestDB2 {
	@GetMapping("/testDB2")
	public Attendee[][][] getObj() {
		
		Attendee[][][] attendees = new Attendee[2][2][2];
		
		
		
		List<String> list = new ArrayList<String>();
		list.add("johnny1");
		list.add("johnny2");
		list.add("johnny3");
		list.add("johnny4");
		
		attendees[0][0][0] =  new Attendee("johnny1","johnny1","0");
		attendees[0][0][1] =  new Attendee("johnny2","johnny2","0");
		attendees[0][1][0] =  new Attendee("johnny3","johnny3","0");
		attendees[0][1][1] =  new Attendee("johnny4","johnny4","0");
		attendees[1][0][0] =  new Attendee("johnny1","johnny1","0");
		attendees[1][0][1] =  new Attendee("johnny4","johnny4","0");
		
		return attendees;
	}
}
