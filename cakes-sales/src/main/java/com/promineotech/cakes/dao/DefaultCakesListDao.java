package com.promineotech.cakes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.cakes.entity.Cakes;
import com.promineotech.cakes.entity.CakesTypes;
import lombok.extern.slf4j.Slf4j;


/*
 * DAO Layer – where tables are accessed from 
 * organized according to data entities (tables) & feeds the data back to the service. 
 * Works directly with data sources (database, queue, another REST service, etc.
 */

@Component
@Slf4j
public class DefaultCakesListDao implements CakesListDao {
	
/*
 * Using NamedParameterJdbcTemplate to avoid SQL injection 
 * Avoided by not concatenating strings together when preparing SQL statements 	
 */
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate; 

	@Override
	public List<Cakes> fetchCakes(CakesTypes type, String flavor) {	
		log.debug("DAO: type={}, flavor={}", type, flavor); 
		
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM cakes "
				+ "WHERE cake_id = :cake_id AND cake_flavor = :cake_flavor"; 
		// @formatter:on

/*
 * added .toString - sql driver cannot convert "type" to a string
 * Will return type as a String instead of as a cake type
 */		
		
		Map<String, Object> params = new HashMap<>();
		params.put("cake_id", type.toString());        
		params.put("cake_flavor", flavor); 
		
/*
 * Using RowMapper to return a list of objects		
 */
		return jdbcTemplate.query(sql, params, new RowMapper<>() {
			@Override
			public Cakes mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Cakes.builder()
						.cake_pk(rs.getLong("cake_pk"))
						.cake_id(CakesTypes.valueOf(rs.getString("cake_id")))								
						.cake_flavor(rs.getString("cake_flavor"))
						.cake_size(rs.getInt("cake_size"))
						.num_tiers(rs.getInt("num_tiers"))
						.cake_shape(rs.getString("cake_shape"))
						.build();				
				// @formatter:on
			}});
	}
}
