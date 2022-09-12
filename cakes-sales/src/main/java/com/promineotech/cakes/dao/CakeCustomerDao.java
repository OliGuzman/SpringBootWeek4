package com.promineotech.cakes.dao;

import java.util.List;

import com.promineotech.cakes.entity.Customers;

public interface CakeCustomerDao {
	
	/**
	 * 
	 * @return
	 */
	List<Customers> listOfCustomers();

	/**
	 * 
	 * @param customer_pk
	 * @return
	 */
	List<Customers> fetchCustomerUsingId(Long customer_pk);

	/**
	 * 
	 * @param customer_pk
	 * @param customer_id
	 * @param first_name
	 * @param last_name
	 * @param phone_number
	 */
	void addNewCustomer(Long customer_pk, String customer_id, String first_name, String last_name, String phone_number);

	/**
	 * 
	 * @param phone_number
	 * @param customer_pk
	 */
	void updatePhoneUsingId(Long customer_pk, String phone_number);

	/**
	 * 
	 * @param customer_pk
	 */
	void deleteCustomerUsingId(Long customer_pk);

}
