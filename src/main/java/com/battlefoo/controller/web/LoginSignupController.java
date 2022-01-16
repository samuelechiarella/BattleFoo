package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginSignupController {
	@GetMapping("/logout")
	public String logout(HttpServletRequest req){
		req.getSession(true).setAttribute("loggedUser", "");
		return "index";
	}
}
