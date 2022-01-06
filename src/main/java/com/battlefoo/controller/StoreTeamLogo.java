package com.battlefoo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.battlefoo.model.ServerPaths;


@RestController
public class StoreTeamLogo {
	@PostMapping("/storeTeamLogo")
	public String storeImage(@RequestBody String imageDataURL,
								HttpServletResponse resp) {
		String result = "Done";
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(new File(ServerPaths.TEAMS_LOGOS_PATH,new Date().getTime() + ".txt")));
			writer.write(imageDataURL);
			writer.close();
		} catch (IOException e) {
			System.out.println("***********************************StoreImage.java ERROR***********************************");
		}
		return result;
	}
}