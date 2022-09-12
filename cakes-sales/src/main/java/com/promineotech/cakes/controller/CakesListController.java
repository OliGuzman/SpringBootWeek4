package com.promineotech.cakes.controller;

import java.util.List;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;    
import com.promineotech.cakes.Constants;
import com.promineotech.cakes.entity.Cakes;
import com.promineotech.cakes.entity.CakesTypes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Adding all openAPI annotations to the interface 
 *  
 * Added the controller-layer of code that intercepts the HTTP request, 
 * sends to service layer, receives the response from service layer & formats the response. 
 * 
 * Added @ RequestMapping to class – any URI with "/cakes" will be mapped to this class
 * 
 * Added @ Validated - turns on Bean Validation
 */

@Validated
@RequestMapping("/cakes")
@OpenAPIDefinition(info = @Info(title = "Cakes Sales Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local Server")})   //server that Swagger will use to perform operations
public interface CakesListController {

/*
 * Added OpenAPI / Swagger documentation to the interface 
 * 
 * Added @ Operation – includes HTTP responses 
 * 
 * Turned formatter off/on to keep the formatting.
 * Helps keep it easier to read 
 */	
	// @formatter:off
	@Operation(
			summary = "Returns a list of cakes options", 
			description = "Returns a list of all cake options given a cake type and/or flavor", 
			responses = {
				@ApiResponse(
					responseCode = "200", 
					description = "A list of cake options is returned.", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = Cakes.class))), 
				@ApiResponse(
					responseCode = "400", 
					description = "The request parameters are invalid.", 
					content = @Content(mediaType = "application/json")), 
				@ApiResponse(
					responseCode = "404", 
					description = "No Cakes were found with the input criteria.", 
					content = @Content(mediaType = "application/json")), 
				@ApiResponse(
					responseCode = "500", 
					description = "An unplanned error accurred.", 
					content = @Content(mediaType = "application/json"))				
			}, 
			parameters = {
				@Parameter(name = "type", 
					allowEmptyValue = false, 
					required = false, 
					description = "Select a type: BUTTER, CARROT, RED VELVET"), 
				@Parameter(name = "flavor", 
					allowEmptyValue = false, 
					required = false, 
					description = "Select a flavor: vanilla, chocolate, carrot, red velvet")
			}
		)
	
/**
 **** G E T ****  R E A D  ****
 *
 * Added @ GetMapping – will map a Get request
 * Get will return a list of jeeps
 *
 * Added @ ResponseStatus – returns response status
 * 
 * Spring is going to map all requests with /cakes to this CakesSalesController class and the fetchCakes method. 
 * Spring is going to map the get requests at /cakes to the fetchCakes method and will return
 * a response status of OK or 200 if it's successful
 * 
 * Added @ RequestParam - Spring will map the parameter "type" based on the name of the parameter 
 * in the method call.
 * 
 */
	
	@GetMapping ("List of Cakes Using Type and Flavor")
	@ResponseStatus(code = HttpStatus.OK)
	List<Cakes> fetchCakes( 
/**
 * will return a list of list of cakes given the 2 parameters: cake type and flavor
 */
			@RequestParam(required = false) 
				CakesTypes type, 
/**
 * Added Bean Validation 
 * limiting length to 30 characters max (helps prevent regular expression denial of service (REDOS) attacks)
 * limiting character classes to a word character & spaces
 */
			@Length(max = Constants.FLAVOR_MAX_LENGTH)
			@Pattern(regexp = "[\\w\\s]*")
			@RequestParam(required = false) 
				String flavor); 
	// @formatter:on	
}
