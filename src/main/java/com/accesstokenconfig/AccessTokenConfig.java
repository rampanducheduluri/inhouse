 package com.accesstokenconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AccessTokenConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTamplate = new RestTemplate();

		return restTamplate;
	}

}
