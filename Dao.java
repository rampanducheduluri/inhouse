package com.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.controller.Controller;

@Repository
public class Dao {

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public final String queryforlonlivedaccesstoken = "select longlivedaccesstoken from public.facebook where clientid  = :clientId ";

	public String findByclientid(@PathVariable String clientid)
			throws Exception{
		String getToken = null;
		try {
			NamedParameterJdbcTemplate temp = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			parameterSource.addValue("clientId", clientid);
			getToken = temp.queryForObject(queryforlonlivedaccesstoken, parameterSource, String.class);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
		return getToken;
	}
}