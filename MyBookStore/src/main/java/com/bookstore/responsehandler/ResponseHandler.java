package com.bookstore.responsehandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final  class ResponseHandler {
	private ResponseHandler(){};
	 public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
	        Map<String, Object> map = new HashMap<String, Object>();
	            map.put("Result", message);
	            map.put("Http Status", status.value());
	            map.put("Data", responseObj);

	            return new ResponseEntity<Object>(map,status);
	    }
}
