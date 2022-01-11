package com.battlefoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.battlefoo.persistence.dbManagement.Database;

@SpringBootApplication
public class BattleFooApplication {

	public static void main(String[] args) {
		Database.getInstance();
		SpringApplication.run(BattleFooApplication.class, args);
	}
}
