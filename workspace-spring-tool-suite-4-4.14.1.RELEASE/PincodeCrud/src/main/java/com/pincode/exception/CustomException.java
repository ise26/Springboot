package com.pincode.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(PinCodeNotFoundException.class)
	public final ResponseEntity<ExceptionPOJO> PinCodeNotFoundException(Exception ex, WebRequest web){
		
		ExceptionPOJO ex1 = new ExceptionPOJO(ex.getMessage(), web.getDescription(false), new Date());
		
		return new ResponseEntity(ex1,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoDataException.class)
	public final ResponseEntity<ExceptionPOJO> NoDataEx(Exception ex,WebRequest web){
		ExceptionPOJO ex1 =  new ExceptionPOJO(ex.getMessage(),web.getDescription(false),new Date());
		
		return new ResponseEntity<ExceptionPOJO>(ex1,HttpStatus.NO_CONTENT);
	}
}
