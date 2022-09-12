package com.promineotech.cakes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.cakes.entity.Customers;
import com.promineotech.cakes.service.CakeCustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCakeCustomerController implements CakeCustomerController{
	
	@Autowired
	private CakeCustomerService cakeCustomerService; 
	
	@Override
	public List<Customers> listOfCustomers() {
		log.info("List of Employees");
		
		return cakeCustomerService.listOfCustomers();
	}
	
	@Override
	public ResponseEntity<List<Customers>> fetchCustomerUsingId(Long customer_pk) {          //get a customer by their Id
		log.info("Request a specific customer using their Id", customer_pk);
		
		List<Customers> customers = cakeCustomerService.fetchCustomerUsingId(customer_pk); 
		
		return new ResponseEntity<List<Customers>>(customers, HttpStatus.OK);
	}

	@Override
	public void addNewCustomer(Long customer_pk, String customer_id, String first_name, String last_name, String phone_number) {   //add a new customer
		log.info("Request to add a new customer");
		
		cakeCustomerService.addNewCustomer(customer_pk, customer_id, first_name, last_name, phone_number);		
	}

	@Override
	public void updatePhoneUsingId(Long customer_pk, String phone_number) {               //update customer phone number
		log.info("Request to update customer phone number", customer_pk);
		
		cakeCustomerService.updatePhoneUsingId(customer_pk, phone_number); 	
	}

	@Override
	public void deleteCustomerUsingId(Long customer_pk) {                                   //delete a customer
		log.info("Request to delete a customer");
		
		cakeCustomerService.deleteCustomerUsingId(customer_pk); 		
	}
}
