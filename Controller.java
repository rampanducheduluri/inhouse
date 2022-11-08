package com.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.exception.BusinessException;
import com.pojo.Pojo;
import com.servicelayer.ServiceLayer;

@RestController
public class Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private final ServiceLayer servicelayer;

	public Controller(ServiceLayer servicelayer) {
		super();
		this.servicelayer = servicelayer;
	}

	Pojo accessTokenPojo;

	@GetMapping("/image/{clientid}")
	public boolean findByclientid(@PathVariable String clientid, @RequestBody Map<String, String> requestBody)
			throws Exception {
       boolean result = false;
		try {
			String longlivedaccesstoken = servicelayer.findByclientid(clientid);
			System.out.println(longlivedaccesstoken);

			String imageUrl = requestBody.get("url");
			System.out.println(imageUrl);

			Pojo tokenObject = servicelayer.consumeAccesstoken(longlivedaccesstoken);
			System.out.println(tokenObject);
			String accesstoken = tokenObject.getAccesstoken();
			String id = tokenObject.getId();
			 result = servicelayer.consumeImagePost(accesstoken, id, imageUrl);
			LOGGER.info(clientid);
			if (!result)
				throw new BusinessException("Unable to Post the Image");
			
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage());
		//	throw new Exception("Invalid client Id");
		}
		return result;
	}

	@GetMapping("/text/{clientid}")
	public boolean findByclientid1(@PathVariable String clientid ,@RequestBody Map<String, String> requestBody) throws Exception {
          boolean resultVerification = false;
		try {
			String longlivedaccesstoken = servicelayer.findByclientid(clientid);
			String message = requestBody.get("message");
			
			Pojo tokenObject = servicelayer.consumeAccesstoken(longlivedaccesstoken);


			String accesstoken = tokenObject.getAccesstoken();
			String id = tokenObject.getId();
			 resultVerification = servicelayer.consumeTextPost(accesstoken, id,message);

			LOGGER.info(clientid);
			if (!resultVerification)
				throw new BusinessException("Unable to Post the Image");
			

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			System.out.println("invalied login credentials");
			//throw new Exception("Invalid Client Id");
		}
		return resultVerification;

	}
}