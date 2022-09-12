package com.promineotech.cakes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promineotech.cakes.entity.Packages;

@Service
public interface CakePackagesServices {
	
	/**
	 * 
	 * @return
	 */
	List<Packages> listOfPackages();

}
