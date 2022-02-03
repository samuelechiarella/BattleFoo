package com.battlefoo.controller.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.CommonMethods;
import com.battlefoo.model.entitiesObjects.Tournament;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class HomePage {
	@GetMapping("/")
	public String getHomePage1(HttpServletRequest req) {
		
		CommonMethods.updateGamesAttribute(req);
		
		if(req.getSession(true).getAttribute("loggedUser")==null) {
			/*BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(new File(ServerPaths.LOG_PATH,"log.txt"), true));
				writer.append(req.getRemoteAddr() + ":" + req.getRemotePort() + " DATE: " +
							new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis())) + "\n");
				writer.close();
			} catch (IOException e) {
				System.out.println("***********************************HomePage.java ERROR***********************************");
			}*/
			//SETTING RANDOM TOURNAMENTS
			List<Tournament> tournaments = Database.getInstance().getAllTournaments();
			List<Tournament> randomTournaments = new ArrayList<Tournament>();
			for(Tournament t1: tournaments) {
				if(new Random().nextBoolean())
					randomTournaments.add(t1);
			}
			for(Tournament t : randomTournaments) {
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
			req.getSession(true).setAttribute("randomTournaments", randomTournaments);
			//**********************************************************************************
			
			
			return "index";
		}
		
		CommonMethods.updateTeamsAttribute(req);
		CommonMethods.updateOrganizationsAttribute(req);
		
		
		
		return "index";
	}
	
	@GetMapping("/index")
	public String getHomePage2(HttpServletRequest req) {
		if(req.getSession(true).getAttribute("loggedUser") != null) {
			CommonMethods.updateTeamsAttribute(req);
			CommonMethods.updateOrganizationsAttribute(req);
		}
		
		
		//SETTING RANDOM TOURNAMENTS
		List<Tournament> tournaments = Database.getInstance().getAllTournaments();
		List<Tournament> randomTournaments = new ArrayList<Tournament>();
		for(Tournament t1: tournaments) {
			if(new Random().nextBoolean())
				randomTournaments.add(t1);
		}
		for(Tournament t : randomTournaments) {
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
		req.getSession(true).setAttribute("randomTournaments", randomTournaments);
		//**********************************************************************************
		
		
		CommonMethods.updateGamesAttribute(req);
		return "index";
	}
}
