package com.battlefoo.controller.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.entitiesObjects.Team;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class HomePage {
	@GetMapping("/")
	public String getHomePage(HttpServletRequest req) {
		//	set attribute teamsList
		HttpSession session = req.getSession(true);
		List<Team> teams = Database.getInstance().getAllTeams();
		System.out.println(teams);
		session.setAttribute("teamsList", teams);
		// 	***********************
		
		req.getSession(true).setAttribute("loggedUser", "user1");
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(new File(ServerPaths.LOG_PATH,"log.txt"), true));
			writer.append(req.getRemoteAddr() + ":" + req.getRemotePort() + " DATE: " +
						new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis())) + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("***********************************HomePage.java ERROR***********************************");
		}
		return "index";
	}
}
