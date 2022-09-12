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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.cakes.entity.Customers;

@Component
@Service
@Transactional
public class DefaultCakeCustomerDao implements CakeCustomerDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Customers> listOfCustomers() {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM customers"; 
		// @formatter:on
		
		return jdbcTemplate.query(sql, new RowMapper<>() {
			
			@Override
			public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Customers.builder()
						.customer_pk(rs.getLong("customer_pk"))
						.customer_id(rs.getString("customer_id"))								
						.first_name(rs.getString("first_name"))
						.last_name(rs.getString("last_name"))
						.phone_number(rs.getString("phone_number"))							
						.build();
				// @formatter:on
			}	
		});		
	}

	@Override
	public List<Customers> fetchCustomerUsingId(Long customer_pk) {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM customers "
				+ "WHERE customer_pk = :customer_pk"; 
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("customer_pk", customer_pk);        
		
		return jdbcTemplate.query(sql, params, new RowMapper<>() {
			
			@Override
			public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Customers.builder()	
						.customer_pk(rs.getLong("customer_pk"))
						.customer_id(rs.getString("customer_id"))								
						.first_name(rs.getString("first_name"))
						.last_name(rs.getString("last_name"))
						.phone_number(rs.getString("phone_number"))
						.build();				
				// @formatter:on
			}
		});
	}
	
	@Override
	public void addNewCustomer(Long customer_pk, String customer_id, String first_name, String last_name, String phone_number) {
		// @formatter:off
		String sql = ""
				+ "INSERT INTO customers ("
				+ "customer_pk, customer_id, first_name, last_name, phone_number "
				+ ") VALUES ("
				+ ":customer_pk, :customer_id, :first_name, :last_name, :phone_number "
				+ ")"; 				
		// @formatter:on

		Map<String, Object> params = new HashMap<>();
		//params.put("customer_pk", customer_pk); 
		params.put("customer_pk", customer_pk);
		params.put("customer_id",  customer_id);
		params.put("first_name", first_name);
		params.put("last_name", last_name);
		params.put("phone_number", phone_number);

		jdbcTemplate.update(sql, params);		
	}

	@Override
	public void updatePhoneUsingId(Long customer_pk, String phone_number) {
		// @formatter:off
		String sql = ""
				+ "UPDATE customers "
				+ "SET phone_number = :phone_number "
				+ "WHERE customer_pk = :customer_pk";
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();		
		params.put("customer_pk", customer_pk);
		params.put("phone_number", phone_number);
		
		jdbcTemplate.update(sql, params);		
	}

	@Override
	public void deleteCustomerUsingId(Long customer_pk) {
		// @formatter:off
		String sql = ""
				+ "DELETE FROM customers "
				+ "WHERE customer_pk = :customer_pk"; 
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("customer_pk", customer_pk);
		
		jdbcTemplate.update(sql, params);	
	}
}
