package com.promineotech.cakes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.cakes.entity.Packages;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/packages")
@OpenAPIDefinition(info = @Info(title = "Cake Packages"), servers = {
		@Server(url = "http://localhost:8080", description = "Local Server")}) 
public interface CakePackagesController {
	
		// @formatter:off
			@Operation(
					summary = "Returns a list of available cake packaging options", 
					description = "Returns a list of all the package options for cake orders", 
					responses = {
						@ApiResponse(
							responseCode = "200", 
							description = "A list of packagesis returned", 
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = Packages.class))), 
						@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid.", 
							content = @Content(mediaType = "application/json")), 
						@ApiResponse(
							responseCode = "404", 
							description = "No packages were found with the input criteria", 
							content = @Content(mediaType = "application/json")), 
						@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error accurred", 
							content = @Content(mediaType = "application/json"))})
			@GetMapping("Package Options")
			@ResponseStatus(code = HttpStatus.OK)
			//ResponseEntity<List<Customers>> listOfCustomers();       //READ - Retrieves full list of customers
			List<Packages> listOfPackages(); 
	
	
}
