package com.pincode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pincode.entity.Pincodes;

public interface PincodeService {

	public List<Pincodes> getAllData(Pincodes data);

	public List<Pincodes> getAllDataFromCityAndPincode(Pincodes data) throws Exception;

	public List<Pincodes> getAllDataForJSON();

	public List<Pincodes> getDataByState(String pincode);
	
	public List<String> getUniqeStates();
	
	public List<Integer> getDataByCity(String city);
}
