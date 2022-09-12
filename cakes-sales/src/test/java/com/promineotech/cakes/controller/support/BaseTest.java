package com.promineotech.cakes.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import lombok.Getter;

/*
 * Base test class - available for all our tests
 * 
 * When Spring boot starts off the test, it creates an application context where it holds all the
 * environment configuration, all the stuff needed for a test/application to run. It goes through 
 * all the classes it knows about & fills in instance variables within the classes, calls methods,  
 * aka Dependency Injection. 
 * 
 * Spring Boot will fill in the serverPort variable because of the @ LocalServerPort annotation
 * 
 */

abstract class BaseTest {
	@LocalServerPort
	protected int serverPort; 
	
/*
 * @ Autowired tells Spring Boot to inject a copy of the TestRestTemplate 
 * @ Getter creates getters
 */	
	@Autowired
	@Getter
	protected TestRestTemplate restTemplate; 
	
//this is the Uri of the request that is going to be sent to the application 	
	/**
	 * 
	 * @return
	 */
	protected String getBaseUriForCakes() {
		return String.format("http://localhost:%d/Cakes", serverPort); 
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	protected String getBaseUriForOrders() {
//		return String.format("http://localhost:%d/orders", serverPort); 
//	}

}
