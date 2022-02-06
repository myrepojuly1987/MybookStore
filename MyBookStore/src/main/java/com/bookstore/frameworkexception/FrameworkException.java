package com.bookstore.frameworkexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.NOT_FOUND)
public class FrameworkException extends RuntimeException {
	 public FrameworkException(String message) {
	        super(message);
	    }

	    public FrameworkException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
