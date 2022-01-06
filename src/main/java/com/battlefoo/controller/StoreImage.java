package com.battlefoo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StoreImage {
	@PostMapping("/storeImage")
	public String storeImage(@RequestBody String imageDataURL,
								HttpServletResponse resp) {
		String result = "Done";
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("image1.txt"));
			writer.write(imageDataURL);
			writer.close();
		    System.out.println("***************************************DONE**********************************");
		} catch (IOException e) {
			System.out.println("***********************************ERROR*************************************");
		}
		return result;
	}
}