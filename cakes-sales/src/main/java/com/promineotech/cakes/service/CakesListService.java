package com.promineotech.cakes.service;

import java.util.List;
import com.promineotech.cakes.entity.Cakes;
import com.promineotech.cakes.entity.CakesTypes;

public interface CakesListService {

	/**
	 * 
	 * @param flavor
	 * @param shape
	 * @return
	 */
	List<Cakes> fetchCakes(CakesTypes type, String flavor);
}

