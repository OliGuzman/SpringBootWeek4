package com.promineotech.cakes.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Component;
//import com.promineotech.cakes.entity.Cakes;
//import com.promineotech.cakes.entity.CakesTypes;
//import com.promineotech.cakes.entity.Customers;
//import com.promineotech.cakes.entity.Fillings;
//import com.promineotech.cakes.entity.Icings;
//import com.promineotech.cakes.entity.Orders;
//import com.promineotech.cakes.entity.Packages;
//import com.promineotech.cakes.entity.Toppings;


//@Component
//public class DefaultCakesOrderDao implements CakesOrderDao {
//	
//	@Autowired
//	private NamedParameterJdbcTemplate jdbcTemplate; 
//	
//	/**
//	 * 
//	 */	
//	@Override
//	public Orders saveOrder(Customers customers, Cakes cakes, Toppings toppings, 
//			Icings icings, Fillings fillings, List<Packages> packages) {		
//		SqlParams params = generateInsertSql(customers, cakes, toppings, icings, fillings);
////did not add packaging because will create the Order object & then will get the Order PK value that was written
////to the database & then will use that to update the many-to-many relationship between Order packaging with the
////Order packaging table
//		
//		KeyHolder keyHolder = new GeneratedKeyHolder();  //object that receives the primary key when we call update 	
//		jdbcTemplate.update(params.sql, params.source, keyHolder);           //will return the value back to us
//		
//		Long orderPK = keyHolder.getKey().longValue();   //order PK, needed in order to save the packaging
//		savePackages(packages, orderPK); 		      //saves the packaging & the order
//		
//		// @formatter:off
//		return Orders.builder()
//				.orderPK(orderPK)
//				.customers(customers)
//				.cakes(cakes)
//				.toppings(toppings)
//				.icings(icings)
//				.fillings(fillings)
//				.packages(packages)
//				.build();
//		// @formatter:on
//	}
//	
//	/**
//	 * 
//	 * @param packaging
//	 * @param orderPK
//	 */
//	private void savePackages(List<Packages> packages, Long orderPK) { 
//		for(Packages packs : packages) {                  //Packaging object will have the packaging PK, will be saved in OrdersPackaging table
//			SqlParams params = generateInsertSql(packs, orderPK);				
//			jdbcTemplate.update(params.sql, params.source);     //saves the value
//		}		
//	}
//
//	private SqlParams generateInsertSql(Packages packages, Long orderPK) {
//		SqlParams params = new SqlParams();
//		
//		// @formatter:off
//		params.sql = ""
//				+ "INSERT INTO orders_packages ("
//				+ "package_fk, order_fk"
//				+ ") VALUES ("
//				+ ":package_fk, :order_fk"
//				+ ")"; 
//		// @formatter:on
//		
//		params.source.addValue("package_fk", packages.getPackagePK());
//		params.source.addValue("order_fk", orderPK);
//		
//		return params; 
//	}
//
//	/**
//	 * 
//	 * @param customer
//	 * @param cakes
//	 * @param topping
//	 * @param icing
//	 * @param filling
//	 * @return
//	 */
//	private SqlParams generateInsertSql(Customers customers, Cakes cakes, Toppings toppings, Icings icings, Fillings fillings) {
//		// @formatter:off
//		String sql = ""
//				+ "INSERT INTO orders ("
//				+ "customer_fk, cake_fk, topping_fk, icing_fk, filling_fk"
//				+ ") VALUES ("
//				+ ":customer_fk, :cake_fk, :topping_fk, :icing_fk, :filling_fk"
//				+ ")";
//		// @formatter:on		
//		
////Adding to the parameter map
//		SqlParams params = new SqlParams(); 
//		
//		params.sql = sql;
//		params.source.addValue("customer_fk", customers.getCustomerPK()); 
//		params.source.addValue("cake_fk", cakes.getCakePK()); 
//		params.source.addValue("topping_fk", toppings.getToppingPK()); 
//		params.source.addValue("icing_fk", icings.getIcingPK());
//		params.source.addValue("filling_fk", fillings.getFillingPK());		
//		
//		return params; 
//	}
//
//	@Override
//	public List<Packages> fetchPackages(List<String> packagesIds) {
//		if(packagesIds.isEmpty()) {
//			return new LinkedList<>();
//		}
//				
//		Map<String, Object> params = new HashMap<>(); 
//		
//		// @formatter:off
//		String sql = ""
//				+ "SELECT * "
//				+ "FROM packages "
//				+ "WHERE package_id IN(";
//				
//		// @formatter:on 
//		
//		for(int index = 0; index < packagesIds.size(); index++) {
//			String key = "package_id" + index;
//			sql += ":" + key + ", ";
//			params.put(key, packagesIds.get(index));
//		}
//		
//		sql = sql.substring(0, sql.length() - 2);
//		sql += ")";
//				
//		return jdbcTemplate.query(sql, params, new RowMapper<Packages>(){
//
//			@Override
//			public Packages mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// @formatter:off
//				return Packages.builder()
//						.packagePK(rs.getLong("package_pk"))
//						.packageId(rs.getString("package_id"))
//						.packageName(rs.getString("package_name"))
//						.packageContents(rs.getString("package_contents"))						
//						.build();
//				// @formatter:on
//			}});
//	}
//	
//	/**
//	 * 
//	 */
//	@Override
//	public Optional<Customers> fetchCustomers(String customerId) {
//		// @formatter:off
//		String sql = ""
//				+ "SELECT * "
//				+ "FROM customers "
//				+ "WHERE customer_id = :customer_id";
//		// @formatter:on
//		
//		Map<String, Object> params = new HashMap<>(); 
//		params.put("customer_id", customerId);
//
///*
// * Added Optional.ofNullable - if the query return Null, then the optional will be empty
// * If the query returns a value, the optional will have that value 		
// */
//		
//		return Optional.ofNullable(jdbcTemplate.query(sql,  params, new CustomerResultSetExtractor()));		
//	}	
//	
//	/**
//	 * 
//	 */
//	@Override
//	public Optional<Cakes> fetchCakes(CakesTypes types, String flavors, /*int size, int layers, int tiers,*/ String shapes) {
//		// @formatter:off
//		String sql = ""
//				+ "SELECT * "
//				+ "FROM cakes "
//				+ "WHERE cake_id = :cake_id"
//				+ "AND cake_flavor = :cake_flavor"
//				+ "AND cake_shape = :cake_shape";
//		// @formatter:on
//		
//		Map<String, Object> params = new HashMap<>(); 
//		params.put("cake_id", types.toString());
//		params.put("cake_flavor", flavors);
//		params.put("cake_shape", shapes.toString()); 
//				
//		return Optional.ofNullable(jdbcTemplate.query(sql,  params, new CakeResultSetExtractor()));		
//	}
//	
//	/**
//	 * 
//	 */
//	@Override
//	public Optional<Fillings> fetchFillings(String fillingId) {
//		// @formatter:off
//		String sql = ""
//				+ "SELECT * "
//				+ "FROM fillings "
//				+ "WHERE filling_id = :filling_id";
//		// @formatter:on
//		
//		Map<String, Object> params = new HashMap<>(); 
//		params.put("filling_id", fillingId);
//				
//		return Optional.ofNullable(jdbcTemplate.query(sql,  params, new FillingResultSetExtractor()));		
//	}
//	
//	/**
//	 * 
//	 */
//	@Override
//	public Optional<Toppings> fetchToppings(String toppingId) {
//		// @formatter:off
//		String sql = ""
//				+ "SELECT * "
//				+ "FROM toppings "
//				+ "WHERE topping_id = :topping_id";
//		// @formatter:on
//		
//		Map<String, Object> params = new HashMap<>(); 
//		params.put("topping_id", toppingId);
//				
//		return Optional.ofNullable(jdbcTemplate.query(sql,  params, new ToppingResultSetExtractor()));		
//	}
//	
//	/**
//	 * 
//	 */
//	@Override
//	public Optional<Icings> fetchIcings(String icingId) {
//		// @formatter:off
//		String sql = ""
//				+ "SELECT * "
//				+ "FROM icings "
//				+ "WHERE icing_id = :icing_id";
//		// @formatter:on
//		
//		Map<String, Object> params = new HashMap<>(); 
//		params.put("icing_id", icingId);
//				
//		return Optional.ofNullable(jdbcTemplate.query(sql,  params, new IcingResultSetExtractor()));		
//	}
///*
// * Added a class that can be reused	
// */
//	
//	class CustomerResultSetExtractor implements ResultSetExtractor<Customers> {
//		@Override
//		public Customers extractData(ResultSet rs) 
//				throws SQLException, DataAccessException {
//			rs.next(); 
//			// @formatter:off
//			return Customers.builder()
//					.customerId(rs.getString("customer_id"))
//					.customerPK(rs.getLong("customer_pk"))
//					.firstName(rs.getString("first_name"))
//					.lastName(rs.getString("last_name"))
//					.phoneNumber(rs.getString("phone_number"))
//					.build();
//			// @formatter:on
//		}		
//	}
//	
//	class CakeResultSetExtractor implements ResultSetExtractor<Cakes> {
//		@Override
//		public Cakes extractData(ResultSet rs) 
//				throws SQLException, DataAccessException {
//			rs.next(); 
//			// @formatter:off
//			return Cakes.builder()
//					.cakePK(rs.getLong("cake_pk"))
//					.cakeId(CakesTypes.valueOf(rs.getString("cake_id")))
//					.cakeFlavor(rs.getString("cake_flavor"))
//					.cakeSize(rs.getInt("cake_size"))
//					.numTiers(rs.getInt("num_tiers"))
//					.cakeShape(rs.getString("cake_shape"))								
//					.build();
//			// @formatter:on
//		}		
//	}
//	
//	class FillingResultSetExtractor implements ResultSetExtractor<Fillings> {
//		@Override
//		public Fillings extractData(ResultSet rs) 
//				throws SQLException, DataAccessException {
//			rs.next(); 
//			// @formatter:off
//			return Fillings.builder()
//					.fillingPK(rs.getLong("filling_pk"))
//					.fillingId(rs.getString("filling_id"))												
//					.build();
//			// @formatter:on
//		}		
//	}
//	
//	class ToppingResultSetExtractor implements ResultSetExtractor<Toppings> {
//		@Override
//		public Toppings extractData(ResultSet rs) 
//				throws SQLException, DataAccessException {
//			rs.next(); 
//			// @formatter:off
//			return Toppings.builder()
//					.toppingPK(rs.getLong("topping_pk"))
//					.toppingId(rs.getString("topping_id"))												
//					.build();
//			// @formatter:on
//		}		
//	}
//	
//	class IcingResultSetExtractor implements ResultSetExtractor<Icings> {
//		@Override
//		public Icings extractData(ResultSet rs) 
//				throws SQLException, DataAccessException {
//			rs.next(); 
//			// @formatter:off
//			return Icings.builder()
//					.icingPK(rs.getLong("icing_pk"))
//					.icingId(rs.getString("icing_id"))												
//					.build();
//			// @formatter:on
//		}		
//	}
//	
///*
// * Generating the sql statements & parameter map	
// * 
// * Using MapSqlParameterSource - is a Hash Map with an @ Param method instead of put
// * 
// */
//	class SqlParams {
//		String sql; 
//		MapSqlParameterSource source = new MapSqlParameterSource();  		
//	}
//}
