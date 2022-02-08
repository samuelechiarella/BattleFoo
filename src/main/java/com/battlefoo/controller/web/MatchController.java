package com.battlefoo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {
	
	@GetMapping("/matchPage")
	public String getMatchPage() {
		return "matchPage";
	}
}
