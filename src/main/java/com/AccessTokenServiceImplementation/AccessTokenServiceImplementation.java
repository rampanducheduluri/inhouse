package com.AccessTokenServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dao.AccessTokenDao;
import com.dto.AccessTokenPojo;

@Service
public class AccessTokenServiceImplementation {
	@Autowired
	private AccessTokenDao accessTokenDao;

	public AccessTokenPojo saveAccessToken(AccessTokenPojo accesTokenPojo) {
		try {
			accessTokenDao.saveAccessToken(accesTokenPojo);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			accessTokenDao.getClass();
		}
		return accesTokenPojo;
	}

	public String findByclientId(@PathVariable String clientId)
			throws IllegalArgumentException, IllegalAccessException {
		return accessTokenDao.findByclientId(clientId);

	}

}
