package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final int Object = 0;

	@ExceptionHandler(NoOrderfoundException.class)
	public final ResponseEntity<Exceptionsentity> handleNoOrderfoundex(Exception ex, WebRequest web){
		Exceptionsentity res = new Exceptionsentity(ex.getMessage(),web.getDescription(false));
		return new ResponseEntity<Exceptionsentity>(res,HttpStatus.NOT_FOUND);
		
	}
}
