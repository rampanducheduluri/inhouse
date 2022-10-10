package com.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.accesstokenservicelayer.AccessTokenServiceLayer;
import com.dto.AccessTokenPojo;

@Service
public class AccessTokenDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private AccessTokenServiceLayer accessTokenServiceLayer;

	public static final String SQL_SAVE_ACCESS_TOKEN = "INSERT INTO public.accesstoken1(clientid,secretcode,accesstoken) values(:clientId,:secretCode,:accessToken)";

	public AccessTokenPojo saveAccessToken(AccessTokenPojo accesTokenPojo) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue("clientId", accesTokenPojo.getClientId());
			params.addValue("secretCode", accesTokenPojo.getSecretCode());
			params.addValue("accessToken", accesTokenPojo.getAccess_token());

			template.update(SQL_SAVE_ACCESS_TOKEN, params);
			// accesTokenPojo.setClientId(SQL_SAVE_ACCESS_TOKEN);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

		return accesTokenPojo;
	}

	public String findByclientId(@PathVariable String clientId)
			throws IllegalArgumentException, IllegalAccessException {
		final String sqlquery = "select accessToken from accesstoken1 where clientid='" + clientId + "';";
		AccessTokenPojo accessToken;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
		return rows.get(0).get("accesstoken").toString();
	}
}
