package com.battlefoo.controller.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.battlefoo.ServerPaths;
import com.battlefoo.model.Response;
import com.battlefoo.model.entitiesObjects.User;
import com.battlefoo.persistence.dbManagement.Database;

@RestController
public class LoginSignupRestController {
	
	@GetMapping("/logout")
	public Response logout(HttpServletRequest req){
		Enumeration<String> e = req.getSession(true).getAttributeNames();
		while(e.hasMoreElements()) {
			String s = e.nextElement();
			req.getSession(true).removeAttribute(s);
		}
		return new Response(Response.success, "Redirect to index");
	}
	
	@PostMapping("/login")
	public Response login(HttpServletRequest req, @RequestBody User user){
		Response response = createCreateLoginResponse(user);
		
		if(response.getResponseCode()==200) {
			req.getSession(true).setAttribute("loggedUser", user.getUsername());
			req.getSession(true).setAttribute("loggedPlayer", Database.getInstance().getPlayerByUsername(user.getUsername()));
			
			if(Database.getInstance().managerExists(user.getUsername()))
				req.getSession(true).setAttribute("loggedManager", Database.getInstance().getManagerByUsername(user.getUsername()));
		}
		
		return response;
	}
	
	@PostMapping("/signup")
	public Response signup(HttpServletRequest req, @RequestBody User user){
		user.setProfilePicture(ServerPaths.DEFAULT_PROFILE_PICTURE);
		Response response = createSignupResponse(user);
		if(response.getResponseCode()==200) {
			req.getSession(true).setAttribute("loggedUser", user.getUsername());
			req.getSession(true).setAttribute("loggedPlayer", Database.getInstance().getPlayerByUsername(user.getUsername()));
		}
		
		return response;
	}
	
	@PostMapping("/signupManager")
	public Response signupManager(HttpServletRequest req, @RequestBody String password) {
		Response response = createSignupManagerResponse(req, password.replace("\"", ""));
		return response;
	}

	private Response createCreateLoginResponse(User user) {
		Response res = new Response(Response.failure,"Storing failed");
		if(Database.getInstance().allowLogIn(user.getUsername(), user.getPassword())) {
			res.setResponseCode(Response.success);
			res.setResponseMessage("Logged");
		}
		else
			res.setResponseMessage("Log in failed");
		return res;
	}
	
	private Response createSignupResponse(User user) {
		Response res = new Response(Response.failure,"Storing failed");
		if(!emailAccepted(user.getEmail())) {
			res.setResponseCode(501);
			res.setResponseMessage("Invalid email");
			return res;
		}
		if(!Database.getInstance().playerExists(user.getEmail())) {
			if(!Database.getInstance().playerExists(user.getUsername())) {
				Database.getInstance().insertUser(user);
				res.setResponseCode(200);
				res.setResponseMessage("Users added");
			}
			else {
				res.setResponseCode(503);
				res.setResponseMessage("Username already exists!");
			}
		}
		else {
			res.setResponseCode(502);
			res.setResponseMessage("Email already exists!");
		}
		return res;
	}
	
	private boolean emailAccepted(String email) {
		return true;
	}
	
	private Response createSignupManagerResponse(HttpServletRequest req, String password) {
		Response res = new Response(Response.failure, "Sign up as Manager failed");
		String username = (String)req.getSession(true).getAttribute("loggedUser");
		if(Database.getInstance().allowLogIn(username, password)) {
			if(Database.getInstance().insertManager(username)) {
				res.setResponseCode(200);
				res.setResponseMessage("User signed up ad Manager");
				req.getSession(true).setAttribute("loggedManager", Database.getInstance().getManagerByUsername(username));
			}
			else {
				res.setResponseCode(502);
				res.setResponseMessage("Storing manager failed!");
			}
		}
		else {
			res.setResponseCode(501);
			res.setResponseMessage("Invalid password!");
		}
		return res;
	}
	
	@PostMapping("/loginWithGoogle")
	public Response loginWithGoogle(HttpServletRequest req, @RequestBody String username) {
		Response response = new Response(Response.failure, "Loggin in with Google failed!");
		username = username.replace("\"", "");
		if(Database.getInstance().playerExists(username)) {
			response.setResponseCode(200);
			response.setResponseMessage("User logged with Google");
			req.getSession(true).setAttribute("loggedUser", username);
			req.getSession(true).setAttribute("loggedPlayer", Database.getInstance().getPlayerByUsername(username));
		}
		return response;
	}
}
