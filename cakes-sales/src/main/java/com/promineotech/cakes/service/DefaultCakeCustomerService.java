package com.promineotech.cakes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.cakes.dao.CakeCustomerDao;
import com.promineotech.cakes.entity.Customers;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCakeCustomerService implements CakeCustomerService{
	
	@Autowired
	private CakeCustomerDao cakeCustomerDao;

	@Transactional (readOnly = true)
	@Override
	public List<Customers> listOfCustomers() {
		log.debug("The listOfCustomers method is called");
		List<Customers> customers = cakeCustomerDao.listOfCustomers();
		
		if(customers.isEmpty()) {
			String msg = String.format("No customers were found");
			throw new NoSuchElementException(msg);
		}
		return customers;
	}
	
	@Transactional (readOnly = true)
	@Override
	public List<Customers> fetchCustomerUsingId(Long customer_pk) {	
		log.info("The fetchCustomerUsingId method was called with customer_pk={}", customer_pk);
		
		List<Customers> customers = cakeCustomerDao.fetchCustomerUsingId(customer_pk);
		
		if(customers.isEmpty()) {
			String msg = String.format("No customer found with customer_pk=%s", customer_pk);
			throw new NoSuchElementException(msg); 
		}
		
		return customers;				
	}
	
	@Transactional (readOnly = false)
	@Override
	public void addNewCustomer(Long customer_pk, String customer_id, String first_name, String last_name, String phone_number) {
		cakeCustomerDao.addNewCustomer(customer_pk, customer_id, first_name, last_name, phone_number);		
	}

	@Transactional (readOnly = false)
	@Override
	public void updatePhoneUsingId(Long customer_pk, String phone_number) {
		cakeCustomerDao.updatePhoneUsingId(customer_pk, phone_number); 		
	}

	@Transactional (readOnly = false)
	@Override
	public void deleteCustomerUsingId(Long customer_pk) {
		cakeCustomerDao.deleteCustomerUsingId(customer_pk);		
	}
}