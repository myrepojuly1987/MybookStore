package com.bookstore.frameworkexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.NOT_FOUND)
public final class RecordNotFoundException extends FrameworkException{
	 public RecordNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	 }
}
