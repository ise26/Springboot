package com.pincode.serviceImpl;

import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pincode.entity.Pincodes;
import com.pincode.repository.PincodeDataRepo;
import com.pincode.service.PincodeService;

@Service
public class PincodeServiceImpl implements PincodeService {

	@Autowired
	private PincodeDataRepo pinRepo;
	
	@Override
	public List<Pincodes> getAllData(Pincodes data) {
		
		return pinRepo.findBymasterPincodesName(data.getMasterPincodesName());
	}

	@Override
	public List<Pincodes> getAllDataFromCityAndPincode(Pincodes data){
		return pinRepo.findBymasterPincodesNameAndcity(data.getMasterPincodesName(),data.getCity());
	}

	@Override
	public List<Pincodes> getAllDataForJSON() {
		
		return pinRepo.getAllData();
	}

	@Override
	public List<Pincodes> getDataByState(String pincode) {
		
		return pinRepo.findBystate(pincode);
	}

	@Override
	public List<String> getUniqeStates() {
		
		return pinRepo.findallstate();
	}

	@Override
	public List<Integer> getDataByCity(String city) {

		return pinRepo.findcity(city);
	}

}
