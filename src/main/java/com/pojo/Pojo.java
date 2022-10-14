package com.pojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@Configuration
public class Pojo {
	
	
public Pojo(String accesstoken, String id) {
		this.accesstoken=accesstoken;
		this.id =id;
	}
	
	private String id;
	private String accesstoken;

}
