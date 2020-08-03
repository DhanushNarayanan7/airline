package com.dhanush.airline.exception;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ResourceNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9155198511635265506L;
	private String errorCode;
	private String errorMessage;
	private LocalDateTime errorTimestamp;
	
	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String errorCode, String errorMessage, LocalDateTime errorTimestamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTimestamp = errorTimestamp;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getErrorTimestamp() {
		return errorTimestamp;
	}
	public void setErrorTimestamp(LocalDateTime errorTimestamp) {
		this.errorTimestamp = errorTimestamp;
	}
	

}
