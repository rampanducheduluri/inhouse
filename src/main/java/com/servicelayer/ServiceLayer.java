package com.servicelayer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Pojo;

@Service
public class ServiceLayer {

	private final RestTemplate restTemplate;

	@Autowired
	public ServiceLayer(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Pojo consumeAccesstoken() throws Exception {
		String url = "https://graph.facebook.com/v15.0/me/accounts?access_token=EAAOqr18MsrwBAOn2fAfrq2ZBWidYD9jTU7cwT1ft3aTqloiMucaAbSVZBctxkKCTt50Uwey0SEc88NCEH26TIQBe2fQPIRVt1zoZAsHd1ZCqXMgmCtNbZB0KocWsfZBspavwWJqZApxyapyNvOZB3i3jS92WtFonZAZBHNr9jLQCVZC2wZDZD";
		String response = restTemplate.getForObject(url, String.class);
	
		Pojo pojo=new Pojo();
		
		ObjectMapper map = new ObjectMapper();

		LinkedHashMap<String, Object> readValue = map.readValue(response,
				new LinkedHashMap<String, Object>().getClass());

		List<Map<String, Object>> tempList = (List<Map<String, Object>>) readValue.get("data");
		
		String accesstoken = null;
		String id=null;
		
		for (Map<String, Object> tempMap : tempList) {
			if (accesstoken == null) {
				if (tempMap.containsKey("access_token")) {
					accesstoken = String.valueOf(tempMap.get("access_token"));
				}
			}
			id = null;
			for (Map<String, Object> tempMap1 : tempList) {
				if (id == null) {
					if (tempMap1.containsKey("id")) {
						id = String.valueOf(tempMap.get("id"));

					}
				}
				pojo.setAccesstoken(accesstoken);
				pojo.setId(id);
			

			}
		}

		return pojo;

	}

	public String consumePost(String accesstoken, String id) {
		
		Pojo pojo = new Pojo(accesstoken,id);
		pojo.setAccesstoken(accesstoken);
		pojo.setId(id);
		accesstoken = pojo.getAccesstoken();
		id = pojo.getId();
		System.out.println(accesstoken);
		System.out.println(id);
		System.out.println();
   String url = "https://graph.facebook.com/" + id + "/feed?message=inhouse&access_token=" + accesstoken;
   System.out.println(url);
		ResponseEntity<String> response1 = restTemplate.postForEntity(url, null, String.class);//(url, String.class);
				
		return response1.getBody();

	}
}
