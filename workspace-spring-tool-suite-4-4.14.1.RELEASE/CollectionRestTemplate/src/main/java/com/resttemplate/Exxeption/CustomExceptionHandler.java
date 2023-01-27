package com.resttemplate.Exxeption;

import java.util.Date;

import javax.servlet.annotation.HandlesTypes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoDataFoundException.class)
	public final ResponseEntity<Object> NoDataFoundException(Exception ex, WebRequest web){
		
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), web.getDescription(true), new Date());
		
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
}
