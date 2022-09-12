package com.promineotech.cakes.service;

//import java.util.List;
//import java.util.NoSuchElementException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.promineotech.cakes.dao.CakesOrderDao;
//import com.promineotech.cakes.entity.Cakes;
//import com.promineotech.cakes.entity.Customers;
//import com.promineotech.cakes.entity.Fillings;
//import com.promineotech.cakes.entity.Icings;
//import com.promineotech.cakes.entity.Orders;
//import com.promineotech.cakes.entity.OrdersRequest;
//import com.promineotech.cakes.entity.Packages;
//import com.promineotech.cakes.entity.Toppings;
//
//@Service 
//public class DefaultCakesOrderService implements CakesOrderService {
//	
//	@Autowired
//	private CakesOrderDao cakesOrderDao; 
//	
//	@Transactional
//	@Override
//	public Orders createOrder(OrdersRequest ordersRequest) {			
//		Customers customers = getCustomers(ordersRequest); 		
//		Cakes cakes = getTypes(ordersRequest); 				
//		Toppings toppings = getToppings(ordersRequest);		
//		Icings icings = getIcings(ordersRequest);  					
//		Fillings fillings = getFillings(ordersRequest); 
//		List<Packages> packages = getPackages(ordersRequest); 
//				
//		return cakesOrderDao.saveOrder(customers, cakes, toppings, icings, fillings, packages);
//	}
//
//	/**
//	 * 
//	 * @param ordersRequest
//	 * @return
//	 */	
//	private List<Packages> getPackages(OrdersRequest ordersRequest) {		
//		return cakesOrderDao.fetchPackages(ordersRequest.getPackages());					
//	}
//
//	/**
//	 * 
//	 * @param ordersRequest
//	 * @return
//	 */
//	protected Fillings getFillings(OrdersRequest ordersRequest) {
//		return cakesOrderDao.fetchFillings(ordersRequest.getFillings())
//				.orElseThrow(() -> new NoSuchElementException("Filling with ID=" 
//						+ ordersRequest.getFillings() + " was not found"));
//	}
//
//	/**
//	 * 
//	 * @param ordersRequest
//	 * @return
//	 */
//	protected Icings getIcings(OrdersRequest ordersRequest) {
//		return cakesOrderDao.fetchIcings(ordersRequest.getIcings())
//				.orElseThrow(() -> new NoSuchElementException("Icing with ID=" 
//						+ ordersRequest.getIcings() + " was not found"));
//	}
//	
//	/**
//	 * 
//	 * @param ordersRequest
//	 * @return
//	 */
//	protected Toppings getToppings(OrdersRequest ordersRequest) {
//		return cakesOrderDao.fetchToppings(ordersRequest.getToppings())
//				.orElseThrow(() -> new NoSuchElementException("Topping with ID=" 
//						+ ordersRequest.getToppings() + " was not found"));
//	}
//
//	/**
//	 * 
//	 * @param ordersRequest
//	 * @return
//	 */
//	protected Cakes getTypes(OrdersRequest ordersRequest) {
//		return cakesOrderDao.fetchCakes(ordersRequest.getTypes(), ordersRequest.getFlavors(), ordersRequest.getShapes())
//				.orElseThrow(() -> new NoSuchElementException("Cake with ID=" 
//						+ ordersRequest.getTypes() + ", flavor=" 
//						+ ordersRequest.getFlavors() + ", shape=" 
//						+ ordersRequest.getShapes() + " was not found"));
//	}
//
//	/**
//	 * 
//	 * @param ordersRequest
//	 * @return
//	 */
//	protected Customers getCustomers(OrdersRequest ordersRequest) {
//		return cakesOrderDao.fetchCustomers(ordersRequest.getCustomers())
//				.orElseThrow(() -> new NoSuchElementException("Customer with ID=" 
//						+ ordersRequest.getCustomers() + " was not found"));
//	}
//}
// 