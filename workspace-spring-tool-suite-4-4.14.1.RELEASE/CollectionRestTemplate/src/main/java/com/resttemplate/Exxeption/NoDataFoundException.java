package com.resttemplate.Exxeption;

public class NoDataFoundException extends RuntimeException{

	public NoDataFoundException (String message) {
		super(message);
	}
}
