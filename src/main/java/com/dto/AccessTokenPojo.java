package com.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Component
public class AccessTokenPojo {
	
	

//	@Value("${fb.clientId}")
	private String clientId;
//	@Value("$fb.secretCode}")
	private String secretCode;

	@JsonProperty("accesstoken")
	private String access_token;
 public AccessTokenPojo(String access_token)
 {
	 this.access_token=access_token;
 }

}

