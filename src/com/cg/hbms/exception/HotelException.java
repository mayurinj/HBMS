package com.cg.hbms.exception;

//exception class
public class HotelException extends Exception {
	private static final long serialVersionUID = 1L;
	
	//constructors from the super class
	public HotelException() {
		super();
	}

	public HotelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HotelException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelException(String message) {
		super(message);
	}

	public HotelException(Throwable cause) {
		super(cause);
	}

}

