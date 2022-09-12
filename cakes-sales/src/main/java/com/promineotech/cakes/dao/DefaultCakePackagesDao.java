package com.promineotech.cakes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.cakes.entity.Packages;

@Component
@Service
@Transactional
public class DefaultCakePackagesDao implements CakePackagesDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Packages> listOfPackages() {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM packages"; 
		// @formatter:on
		
		return jdbcTemplate.query(sql, new RowMapper<>() {
			
			@Override
			public Packages mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Packages.builder()
						.package_pk(rs.getLong("package_pk"))
						.package_id(rs.getString("package_id"))
						.package_name(rs.getString("package_name"))
						.package_contents(rs.getString("package_contents"))
						.build();
				// @formatter:on
			}	
		});		
	}
}	

