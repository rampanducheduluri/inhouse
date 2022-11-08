package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ErrorMessage businessExceptionHandler(BusinessException businessException)  {
		
		ErrorMessage message = new ErrorMessage();
		message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		message.setMessage(businessException.getLocalizedMessage());
		
		return message;
	}
	
}
