package com.battlefoo.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.User;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class LoginSignupRestController {
	@PostMapping("/login")
	public Response login(HttpServletRequest req, @RequestBody User user){
		Response response = createResponse(user);
		
		if(response.getResponseCode()==200)
			req.getSession(true).setAttribute("loggedUser", user.getUsername());
		
		return response;
	}
	
	private Response createResponse(User user) {
		Response res = new Response(Response.failure,"Storing failed");
		if(Database.getInstance().allowLogIn(user.getUsername(), user.getPassword())) {
			res.setResponseCode(Response.success);
			res.setResponseMessage("Logged");
		}
		else
			res.setResponseMessage("Log in failed");
		return res;
	}
}
