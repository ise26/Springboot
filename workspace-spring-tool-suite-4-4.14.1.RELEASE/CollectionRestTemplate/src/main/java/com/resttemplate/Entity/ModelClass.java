package com.resttemplate.Entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelClass {
	@NotBlank
	private String id_number;
	
	//@NotBlank
	//private String mobile_number;
	
	private Userinfo data;
}
