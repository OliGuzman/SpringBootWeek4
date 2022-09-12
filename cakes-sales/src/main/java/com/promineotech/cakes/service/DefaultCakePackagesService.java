package com.promineotech.cakes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.cakes.dao.CakePackagesDao;
import com.promineotech.cakes.entity.Packages;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCakePackagesService implements CakePackagesServices {
	
	@Autowired
	private CakePackagesDao cakePackagesDao;

	@Transactional (readOnly = true)
	@Override
	public List<Packages> listOfPackages() {
		log.debug("The listOfPackages method is called");
		List<Packages> packages = cakePackagesDao.listOfPackages();
		
		if(packages.isEmpty()) {
			String msg = String.format("No packages were found");
			throw new NoSuchElementException(msg);
		}
		return packages;
	}
}



	
	