package com.pincode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pincodes")
public class Pincodes {

	@Id
	@Column(name = "EXTERNAL_CODE")
	private int externalCode;
	
	@Column(name = "MASTER_PINCODES_NAME")
	private int masterPincodesName;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "STATE")
	private String state;

	@Column(name = "ACTIVE")
	private int active;

	@Column(name = "ETB URBAN")
	private String etbUrban;

	@Column(name = "NTB URBAN")
	private String ntbUrban;

	@Column(name = "RURAL")
	private String rural;
}
