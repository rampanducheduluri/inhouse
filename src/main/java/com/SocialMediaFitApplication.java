package com; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SocialMediaFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaFitApplication.class, args);
	}
	
}
