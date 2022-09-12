package com.promineotech.cakes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.cakes.entity.Cakes;
import com.promineotech.cakes.entity.CakesTypes;
import com.promineotech.cakes.service.CakesListService;
import lombok.extern.slf4j.Slf4j;

/*
 * CONTROLLER LAYER – intercepts HTTP requests, sends it to service layer,
 * receives response back from Service layer & formats the response that goes back to the client.  
 * Responsible for taking the input & passing it off to the service. 
 * Manages the requests & responses. 

 * 
 * Added @ RestController - tells Spring Boot this is a REST controller
 *  (can't do it on the interface)
 *  
 *  Inversion of Control - When an HTTP event comes in, the dispatcher servlet takes control of that, 
 *  looks up in its mapping tableb to see which class & method that should map to. 
 *  Then calls the method. Once the method is called, we have control. 
 *  Once the method is done, we give control back over to Spring.        
 *  
 *  
 * @ RestController is a combination of: 
 *      @ Controller - tells Spring this is a controller class
 *      @ ResponseBody – tells Spring to marshall an incoming payload from JSON to Java objects back & forth 
 *      Anything that gets returned from the service will get mapped to JSON
 *      
 * Added @ Slf4j - Lombok logging annotation – creates a logger - logs the parameters that were input to the method   
 * By default, it logs at the info level, not debug level   
 * 
 * SpringBoot loads its configurations automatically from an application.yaml file in the resources folder. 
 * 
 */

@RestController
@Slf4j
public class DefaultCakesListController implements CakesListController {

/*
 * Used @ Autowired to inject a bean instance(object) into the class  * 
 */
	
	@Autowired
	private CakesListService cakesListService; 
	
	@Override
	public List<Cakes> fetchCakes(CakesTypes type, String flavor) {

// Added log.info - causes the logger to log the type & flavor  
		log.debug("type={}, flavor={}", type, flavor);
		return cakesListService.fetchCakes(type, flavor);
	}
}