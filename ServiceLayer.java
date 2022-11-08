package com.servicelayer;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import com.controller.Controller;
import com.dao.Dao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Pojo;

@Service
public class ServiceLayer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	private final RestTemplate restTemplate;

	@Autowired
	private Dao accessTokenDao;

	@Autowired
	public ServiceLayer(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${longLivedAccessToken}")
	private String longLivedAccessTokenUrl;

	public Pojo consumeAccesstoken(String longlivedaccesstoken) throws Exception {

		try {

			String url = longLivedAccessTokenUrl + longlivedaccesstoken;
			String response = restTemplate.getForObject(url, String.class);

			Pojo pojo = new Pojo();

			ObjectMapper objectMap = new ObjectMapper();

			LinkedHashMap<String, Object> readValue = objectMap.readValue(response,
					new TypeReference<LinkedHashMap<String, Object>>() {
					});
			List<Map<String, Object>> tempList = (List<Map<String, Object>>) readValue.get("data");

			String accesstoken = null;
			String id = null;

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
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public boolean consumeImagePost(String accesstoken, String id, String imageUrl) throws IOException {

		try {
			Pojo pojo = new Pojo(accesstoken, id);
			pojo.setAccesstoken(accesstoken);
			pojo.setId(id);
			accesstoken = pojo.getAccesstoken();
			id = pojo.getId();

			String path = imageUrl;

			FileReader read = new FileReader(
					"C:\\Users\\admin\\eclipse-workspace\\inhouse\\src\\main\\resources\\application.properties");

			Properties values = new Properties();
			values.load(read);

			String url = values.getProperty("imageurl") + id + values.getProperty("imageurl1") + path
					+ values.getProperty("imageurl2") + accesstoken;

			ResponseEntity<String> response1 = restTemplate.postForEntity(url, null, String.class);

			if (response1.getStatusCode().is2xxSuccessful()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	public boolean consumeTextPost(String accesstoken, String id,String message) throws IOException {


		try {
			Pojo pojo = new Pojo(accesstoken, id);
			pojo.setAccesstoken(accesstoken);
			pojo.setId(id);
			accesstoken = pojo.getAccesstoken();
			id = pojo.getId();

			String text = message;

			FileReader read = new FileReader(
					"C:\\Users\\admin\\eclipse-workspace\\inhouse\\src\\main\\resources\\application.properties");

			Properties values = new Properties();
			values.load(read);

			String url = values.getProperty("texturl") + id + values.getProperty("texturl1") + text
					+ values.getProperty("texturl2") + accesstoken;
			System.out.println(url);
			ResponseEntity<String> response1 = restTemplate.postForEntity(url, null, String.class);
			if (response1.getStatusCode().is2xxSuccessful()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	public String findByclientid(@PathVariable String clientid)
			throws IllegalArgumentException, IllegalAccessException {

		try {
			String longlivedaccesstoken = accessTokenDao.findByclientid(clientid);
			return longlivedaccesstoken;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
