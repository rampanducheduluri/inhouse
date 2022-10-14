package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pojo.Pojo;
import com.servicelayer.ServiceLayer;

import ch.qos.logback.classic.Logger;

@RestController
public class Controller {
	
	private final ServiceLayer servicelayer;
	
	@Autowired
	public Controller(ServiceLayer servicelayer)
	{
		this.servicelayer=servicelayer;
	}
	

	
	@PostMapping("message")
	@JsonIgnoreProperties("accesstoken")
	public String postmsg() throws Exception 
	{   
		Pojo tokenObject = servicelayer.consumeAccesstoken();
		
		
		String resultVerification = servicelayer.consumePost(tokenObject.getAccesstoken(), tokenObject.getId());
		
		String accesstoken = tokenObject.getAccesstoken();
		String id = tokenObject.getId();
	    System.out.println(accesstoken);
	    System.out.println(id);

		
		
		
		System.out.println(resultVerification);
		
		return resultVerification;
	}
	

}
