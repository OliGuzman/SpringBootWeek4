 package com.promineotech.cakes;

/**
 * Class with main method to start Spring Boot
 * Starts Spring Boot web application using Tomcat web server 
 * Tomcat is web application container. It runs our application, calls the main method, etc. 
 * Is written in java & handles all browser requests 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promineotech.ComponentScanMarker;

/* 
 * Write code in  Java
 * Build code with Javen
 * Run code on Tomcat
 * 
 * Web application - starts a Tomcat web server (container). Tomcat runs the application, handles
 * all the requests the browser sends it & forwars those to the application. Springboot will map it to methods. 
 * 
 * Added @SpringBootApplication
 * Is made up of: @SpringBootConfiguration-tells SpringBoot this is a configuration class
 *                @EnableAutoConfiguration-turns on Springs Auto-Configuration
 *                @ComponentScan-finds/loads classes/packages, creates an instance of it & puts it in the Bean Registry 
*/

@SpringBootApplication(scanBasePackageClasses = { ComponentScanMarker.class })
public class CakesSales {

	public static void main(String[] args) {
		SpringApplication.run(CakesSales.class, args); 
	}
}
