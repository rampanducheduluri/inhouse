package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configration {

	@Bean
	public RestTemplate restTemplte() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
}
