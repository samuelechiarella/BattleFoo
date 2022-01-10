package com.battlefoo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackgroundController {
	@GetMapping("index")
	public String getHomePage() {
		return "index";
	}
	
	@GetMapping("organization")
	public String getOrganization() {
		return "organization";
	}
}