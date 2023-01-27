package com.example.demo.exception;

public class NoProductFoundException extends RuntimeException{

	public NoProductFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoProductFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoProductFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoProductFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoProductFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
