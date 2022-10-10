package com.accesstokenservicelayer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dto.AccessTokenPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccessTokenServiceLayer {

	private final RestTemplate restTemplate;

	@Autowired
	AccessTokenPojo accessTokenPojo;

	@Autowired
	public AccessTokenServiceLayer(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String consumeAPI(String clientId,String secretCode) throws Exception {
		System.out.println();
		String accessToken;
		String response = restTemplate.getForObject(
				"https://graph.facebook.com/oauth/access_token?client_id=1032095120798396&client_secret=5b8d230d03662cc5e599353ed31aadf0&grant_type=client_credentials",
				String.class);

		// System.out.println(response);
		ObjectMapper mapper = new ObjectMapper();

		LinkedHashMap<String, Object> readValue = mapper.readValue(response,
				new LinkedHashMap<String, Object>().getClass());

		System.out.println(readValue.get("access_token"));

		String accesstoken = (String) readValue.get("access_token");
		return accesstoken;

	}

}
