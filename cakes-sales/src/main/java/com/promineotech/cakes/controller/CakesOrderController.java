package com.promineotech.cakes.controller;

//import javax.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;    
//import com.promineotech.cakes.entity.Orders;
//import com.promineotech.cakes.entity.OrdersRequest;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.servers.Server;
//
///**
// * Added the controller-layer of code that intercepts the HTTP request, 
// * sends to service layer, receives the response from service layer & formats the response. 
// * 
// * Added documentation to describe the controller
// * 
// * Added @ RequestMapping to class – any URI with "/orders" will be mapped to the cakeController class
// * 
// * Added @ Validated - turns on Bean Validation
// */
//
//@Validated
//@RequestMapping("/orders")
//@OpenAPIDefinition(info = @Info(title = "Cakes Orders Service"), servers = {
//		@Server(url = "http://localhost:8080", description = "Local Server")})   //server that Swagger will use to perform operations
//public interface CakesOrderController {
//
///*
// * Added OpenAPI / Swagger documentation to the interface 
// * 
// * Added @ Operation – includes HTTP responses 
// * 
// * Turned formatter off/on to keep the formatting.
// * Helps keep it easier to read 
// */	
//	// @formatter:off
//	@Operation(
//			summary = "Returns an order for a Cake", 
//			description = "Returns the created Cake", 
//			responses = {
//				@ApiResponse(
//					responseCode = "201", 
//					description = "A created Cake is returned.", 
//					content = @Content(mediaType = "application/json", 
//					schema = @Schema(implementation = Orders.class))), 				
//				@ApiResponse(
//					responseCode = "400", 
//					description = "The request parameters are invalid.", 
//					content = @Content(mediaType = "application/json")), 
//				@ApiResponse(
//					responseCode = "404", 
//					description = "A Cake component was not found with the input criteria.", 
//					content = @Content(mediaType = "application/json")), 
//				@ApiResponse(
//					responseCode = "500", 
//					description = "An unplanned error accurred.", 
//					content = @Content(mediaType = "application/json"))				
//			}, 
//			parameters = {
//				@Parameter(name = "ordersRequest", 
//					required = true, 
//					description = "The order as JSON") 
//			}
//		)
//	
///**
// **** P O S T  ****  C R E A T E  ****
// * Added @ PostMapping  
// *
// * Added @ ResponseStatus – returns response status
// * 
// * Added @ RequestBody - binded to an ordersRequest
// * Passing a parameter in the body
// * 
// * Added @ Valid - to validate that the incoming data is correct
// * 
// */
//	
//	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED)
//	Orders createOrder(@Valid @RequestBody OrdersRequest ordersRequest);  
//	
//}
