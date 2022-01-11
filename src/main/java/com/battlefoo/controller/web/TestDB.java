package com.battlefoo.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.battlefoo.model.entitiesObjects.Game;
import com.battlefoo.persistence.dbManagement.Database;

@Controller
public class TestDB {
	
	@GetMapping("/testDB")
	public String getTestDBPage(HttpServletRequest req) {
		getAllGames(req);
		return "testDB";
	}
	
	// Tests
	private void getAllGames(HttpServletRequest req) {
		List<Game> l = Database.getInstance().getAllGames();
		req.setAttribute("allGames", l);
	}
}
