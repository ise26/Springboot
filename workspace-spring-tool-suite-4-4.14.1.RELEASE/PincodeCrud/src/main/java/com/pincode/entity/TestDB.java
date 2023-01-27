package com.pincode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TestDB {
	@Id
	@Column(name = "code")
	private int externalCode;
	
	@Column(name = "MASTER_PINCODES_NAME")
	private int masterPincodesName;
	
	@Column(name = "state")
	private String city;

}
