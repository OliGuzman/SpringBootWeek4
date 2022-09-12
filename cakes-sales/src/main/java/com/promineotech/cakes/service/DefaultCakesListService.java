package com.promineotech.cakes.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.cakes.dao.CakesListDao;
import com.promineotech.cakes.entity.Cakes;
import com.promineotech.cakes.entity.CakesTypes;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * SERVICE LAYER – applies all the business logic/rules. Is organized by use case. 
 * Calls the DAO layer. Takes DAO response, reformats/recombines & pushes it back to the controller. 
 * 
 * Added @ service - tells Spring to manage the bean
 * 
 */

@Service
@Slf4j
public class DefaultCakesListService implements CakesListService { 
	
	@Autowired
	private CakesListDao cakesListDao;

/*
 * Added @ Transactional - read only, so we're not expecting anything to change  	
 */
	
	@Transactional (readOnly = true) 
	@Override
	public List<Cakes> fetchCakes(CakesTypes type, String flavor) {
		log.info("The cakesFetch method was called with type={} and flavor={}",
				type, flavor); 
		
		List<Cakes> cakes =  cakesListDao.fetchCakes(type, flavor);

/*
 * Added an exception handler - if it returns an empty list when it can't find something  		
 */
		
		if (cakes.isEmpty()) {
			String msg = String.format("No cakes found with type=%s and flavor=%s", type, flavor);			
			throw new NoSuchElementException(msg); 
		}
		
		Collections.sort(cakes);		
		return cakes; 
	}
}