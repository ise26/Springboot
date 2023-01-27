package com.example.demo.model;

public class UserErrorResponseDto {
	
	private String status;
	private String error;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "UserErrorResponseDto [status=" + status + ", error=" + error + "]";
	}
	public UserErrorResponseDto(String status, String error) {
		super();
		this.status = status;
		this.error = error;
	}
	
	
}
