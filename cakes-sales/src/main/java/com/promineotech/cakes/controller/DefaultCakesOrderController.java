package com.promineotech.cakes.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.promineotech.cakes.entity.Orders;
//import com.promineotech.cakes.entity.OrdersRequest;
//import com.promineotech.cakes.service.CakesOrderService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@Slf4j
//public class DefaultCakesOrderController implements CakesOrderController {
//	
//	@Autowired
//	private CakesOrderService cakesOrderService; 
//
//	@Override
//	public Orders createOrder(OrdersRequest ordersRequest) {
//		log.debug("Orders={}", ordersRequest);
//		return cakesOrderService.createOrder(ordersRequest);
//	}
//
//}
