package com.promineotech.cakes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.cakes.entity.Packages;
import com.promineotech.cakes.service.CakePackagesServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCakePackagesController implements CakePackagesController {
	
	@Autowired
	private CakePackagesServices cakePackagesService; 

	@Override
	public List<Packages> listOfPackages() {
		log.info("List of Packages");
		
		return cakePackagesService.listOfPackages();
	}
}
