package com.att.salesexpress.poc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

@Repository
public class DbServiceImpl implements DbServiceInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getSiteDataByName(String name) {
		String sql = "SELECT site_data FROM SiteDetail WHERE site_name = ?";
		String siteDataJson = (String) jdbcTemplate.queryForObject(sql, new Object[] { name }, String.class);
		return siteDataJson;
	}

	@Override
	public Map<String, Object> getSiteDetailEntityBySiteName(String sitename) {
		String sql = "SELECT * FROM SiteDetail WHERE site_name = ?";
		Map<String, Object> row = jdbcTemplate.queryForMap(sql, sitename);
		return row;
	}

	public long updateAccessTypeData(final long siteId, final String accessData) throws SQLException {
		String sqlSeq = "SELECT nextval('sitedetail_txn_seq') as trxn_seq";
		final Long seqNum = jdbcTemplate.queryForObject(sqlSeq, Long.class);
		System.out.println(seqNum);
		
		final String sql = "INSERT INTO sitedetail_transactions (id, site_id, access_data) VALUES (?, ?, to_json(?::json))";
		final PGobject jsonObject = new PGobject();
		jsonObject.setType("json");
		jsonObject.setValue(accessData);

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, seqNum);
				pstmt.setLong(2, siteId);
				pstmt.setObject(3, jsonObject);

				return pstmt;
			}
		});
		
		return seqNum;
	}

	@Override
	public String findUserDetailByUserId(String userId) {
		String sql = "SELECT site_data FROM user_detail WHERE user_id = ?";
		String siteDataJson = (String) jdbcTemplate.queryForObject(sql, new Object[] { userId }, String.class);
		return siteDataJson;	
	}
}
