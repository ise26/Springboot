package com.example.demo.rest.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CardException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoMoreDataException.class)
 public final ResponseEntity<com.example.demo.rest.model.CardResponse> handleUserExcepton(Exception ex, WebRequest request)throws Exception{
        
        com.example.demo.rest.model.CardResponse  response = new com.example.demo.rest.model.CardResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<com.example.demo.rest.model.CardResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
