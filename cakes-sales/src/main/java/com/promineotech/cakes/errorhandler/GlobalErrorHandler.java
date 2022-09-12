package com.promineotech.cakes.errorhandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

/*
 * Global Error Handler - manages error messages, result codes, & error logging
 *  
 * Added @ RestControllerAdvice & @ ExceptionHandler to tell Spring this is a Global Error Handler 
 * 
 * Added a handler method that Spring will map an exception to if it catches an exception
 * 
 * How it happens:  Spring will intercept a No Such Element Exception
 * 					It will call the method HandleNoSuchElementException
 *         			Then it'll pass in the exception & return a message   
 * 
 */

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}	
	
	/**
	 * Will handle Method Argument Type Mismatch Exception
	 * If an invalid "Type" is selected
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY); 
	}
	
	/**
	 * Will handle a Constraint Violation Exception
	 * If an invalid "Flavor" is selected
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleConstraintViolationException(
			ConstraintViolationException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY); 
	}
	
	/**
	 * Will handle a No Such Element Exception
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleNoSuchElementException(
			NoSuchElementException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY); 		
	}
	
	/**
	 * Will handle a generic exception
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleException(Exception e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.INTERNAL_SERVER_ERROR, webRequest, LogStatus.STACK_TRACE);
	}
 		
	/**
	 * Will create the Exception message
	 * 
	 * @param e
	 * @param status
	 * @param webRequest
	 * @return
	 */
	private Map<String, Object> createExceptionMessage(Exception e, 
			HttpStatus status, WebRequest webRequest, LogStatus logStatus) {
		Map<String, Object> error = new HashMap<>();
		String timestamp = 
				ZonedDateTime.now().format (DateTimeFormatter.RFC_1123_DATE_TIME); 
		
		if (webRequest instanceof ServletWebRequest) {
			error.put("uri", ((ServletWebRequest) webRequest).getRequest().getRequestURI());  
		}
		
		error.put("message", e.toString());
		error.put("status code", status.value()); 
		error.put("uri", webRequest.getContextPath());
		error.put("timestamp", timestamp); 
		error.put("reason", status.getReasonPhrase()); 
		
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", e.toString());     //logs at the error level the exception message, added toString to log the type as well
		}
		else {
			log.error("Exception: ", e);               //logs the entire stack trace
		}
				
		return error;
	}
}
