package com.battlefoo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackgroundController {
	@GetMapping("/BrowseTournament")
	public String getBrowseTorunament(){
		return "BrowseTournament";
	}
}

