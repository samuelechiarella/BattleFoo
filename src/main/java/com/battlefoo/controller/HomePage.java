package com.battlefoo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.battlefoo.ServerPaths;

@Controller
public class HomePage {
	@GetMapping("/")
	public String getHomePage(HttpServletRequest req) {
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
