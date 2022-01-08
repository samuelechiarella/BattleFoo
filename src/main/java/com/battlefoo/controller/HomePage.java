package com.battlefoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.battlefoo.Database;

@Controller
public class HomePage {
	@GetMapping("/")
	public String getHomePage() {
		Database db = new Database();
		return "index";
	}
}
