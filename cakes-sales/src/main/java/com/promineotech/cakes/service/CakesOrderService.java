package com.promineotech.cakes.service;

import com.promineotech.cakes.entity.Orders;
import com.promineotech.cakes.entity.OrdersRequest;

public interface CakesOrderService {

	/**
	 * 
	 * @param ordersRequest
	 * @return
	 */
	Orders createOrder(OrdersRequest ordersRequest);

}
