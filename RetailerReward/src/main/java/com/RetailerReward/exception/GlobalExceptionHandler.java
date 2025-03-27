package com.RetailerReward.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleCustomerNotFoundException(CustomerNotFoundException ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("error", "Customer Not Found");
	        response.put("message", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("error", "Internal Server Error");
	        response.put("message", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }		
	
}
