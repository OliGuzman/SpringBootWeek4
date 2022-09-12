package com.promineotech.cakes.dao;

import java.util.List;

import com.promineotech.cakes.entity.Cakes;
import com.promineotech.cakes.entity.CakesTypes;

public interface CakesListDao {

	/**
	 * 
	 * @param flavor
	 * @param shape
	 * @return
	 */
	List<Cakes> fetchCakes(CakesTypes type, String flavor);

}
