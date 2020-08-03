package com.dhanush.airline.exception;

import org.springframework.stereotype.Component;

@Component
public class AirlineException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 779679767128057150L;
	
	private ErrorInfo errorInfo;
	
	public AirlineException() {
		
	}
	

	public AirlineException(ErrorInfo errorInfo) {
		super();
		this.errorInfo = errorInfo;
	}


	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	

}
