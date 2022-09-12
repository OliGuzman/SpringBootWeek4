package com.promineotech.cakes.controller;

import java.util.List;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.cakes.Constants;
import com.promineotech.cakes.entity.Customers;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/customers")
@OpenAPIDefinition(info = @Info(title = "Cake Customer Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local Server")})   //server that Swagger will use to perform operations
public interface CakeCustomerController {	
	
	// @formatter:off
		@Operation(
				summary = "Returns a list of all customers", 
				description = "Returns a list of all the customers on the database", 
				responses = {
					@ApiResponse(
						responseCode = "200", 
						description = "A list of customers is returned", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Customers.class))), 
					@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid.", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "404", 
						description = "No customers were found with the input criteria", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error accurred", 
						content = @Content(mediaType = "application/json"))})
		@GetMapping("List of Customers - READ")
		@ResponseStatus(code = HttpStatus.OK)
		//ResponseEntity<List<Customers>> listOfCustomers();       //READ - Retrieves full list of customers
		List<Customers> listOfCustomers(); 
		
		
		
		// @formatter:off
		@Operation(
				summary = "Returns a customer by Id", 
				description = "Returns a customer using the Id number", 
				responses = {
					@ApiResponse(
						responseCode = "200", 
						description = "Returns a customer using given id number", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Customers.class))), 
					@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid.", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "404", 
						description = "No customer was found with the input criteria.", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error accurred.", 
						content = @Content(mediaType = "application/json"))				
				}, 
				parameters = {
					@Parameter(name = "customer_pk", 
						allowEmptyValue = false, 
						required = false, 
						description = "The customer Id number")
				}
			)
		@GetMapping("Customer by Id - READ")            
		@ResponseStatus(code = HttpStatus.OK)
		ResponseEntity<List<Customers>> fetchCustomerUsingId(       //READ - Retrieves a customer by customer_id
			@RequestParam(required = true) 
				Long customer_pk); 

		
		
		// @formatter:off
		@Operation(
				summary = "Adds a new customer - CREATE", 
				description = "Adds a new customer to the database", 
				responses = {
					@ApiResponse(
						responseCode = "200", 
						description = "A customer has been added", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Customers.class))), 
					@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "404", 
						description = "No customer has been added", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error accurred.", 
						content = @Content(mediaType = "application/json"))				
				}, 
				parameters = {
					@Parameter(name = "customer_pk", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the customer Id number"), 
					@Parameter(name = "customer_id", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the bakery custom customer id using format: firstName_lastName"), 
					@Parameter(name = "first_name", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the customer first name"),
					@Parameter(name = "last_name", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the customer last name"), 
					@Parameter(name = "phone_number", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the customer phone number using format: NNN-NNN-NNNN"), 
				}
			)
		@PostMapping("Add Customer - CREATE")
		@ResponseStatus(code = HttpStatus.CREATED)	
		void addNewCustomer(                                    //CREATE - Adds a new customer
				
			@RequestParam(required = true) 		
				Long customer_pk,					
			@RequestParam(required = true) 		
				String customer_id,					
			@Length(max = Constants.FIRSTNAME_MAX_LENGTH)
			@Pattern(regexp = "[\\w\\s]*")
			@RequestParam(required = true) 		
				String first_name, 			
			@Length(max = Constants.LASTNAME_MAX_LENGTH)
			@Pattern(regexp = "[\\w\\s]*")
			@RequestParam(required = true) 
				String last_name,	
			@RequestParam(required = true) 
				String phone_number);	
		
		
		
		// @formatter:off
		@Operation(
				summary = "Updates a customer phone number", 
				description = "Updates the customer phone number to the new number provided", 
				responses = {
					@ApiResponse(
						responseCode = "200", 
						description = "Customer phone number has been updated", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Customers.class))), 
					@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid.", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "404", 
						description = "The phone number has not been updated using the new input criteria", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error accurred.", 
						content = @Content(mediaType = "application/json"))				
				}, 
				parameters = {					
					@Parameter(name = "customer_pk", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the customer id number"),
					@Parameter(name = "phone_number", 
					allowEmptyValue = false, 
					required = false, 
					description = "Enter the new phone number using format: NNN-NNN-NNNN") 
				}
			)
		@PutMapping("Update Phone Number - UPDATE")
		@ResponseStatus(code = HttpStatus.OK)
		void updatePhoneUsingId(                              //UPDATE a customer phone number using their Id
								
				@RequestParam(required = true)
				Long customer_pk,
		
				@RequestParam(required = true)
				String phone_number);
		
		
		
		// @formatter:off
		@Operation(
				summary = "Delete a customer", 
				description = "Delete a customer using customer id", 
				responses = {
					@ApiResponse(
						responseCode = "200", 
						description = "The customer has been deleted", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Customers.class))), 
					@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid.", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "404", 
						description = "No customer has been deleted using the input criteria", 
						content = @Content(mediaType = "application/json")), 
					@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error accurred.", 
						content = @Content(mediaType = "application/json"))				
				}, 
				parameters = {					
					@Parameter(name = "customer_pk", 
						allowEmptyValue = false, 
						required = false, 
						description = "Enter the customer id number")
				}
			)
		@DeleteMapping("Delete Customer - DELETE")
		@ResponseStatus(code = HttpStatus.OK)
		void deleteCustomerUsingId(                           //DELETE a Customer using their Id
				@RequestParam(required = true)
				Long customer_pk);
	// @formatter:on	
}
