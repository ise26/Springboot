package com.example.demo.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intercept")
public class InterceptEntity {

	@Id
	private Integer id;
	private String time;
	private String request;
	private String response;
	private String contactNumber;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public InterceptEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public InterceptEntity(Integer id, String time, String request, String response, String contactNumber) {
		super();
		this.id = id;
		this.time = time;
		this.request = request;
		this.response = response;
		this.contactNumber = contactNumber;
	}
	public InterceptEntity(Integer id, String time, String request, String response) {
		super();
		this.id = id;
		this.time = time;
		this.request = request;
		this.response = response;
	}
	
	
}
