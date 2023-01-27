package com.pincode.controller;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pincode.entity.Pincodes;
import com.pincode.exception.PinCodeNotFoundException;
import com.pincode.repository.PincodeDataRepo;
import com.pincode.service.PincodeService;
import com.pincode.serviceImpl.PincodeServiceImpl;

@RestController
public class PincodeController {

	@Autowired
	private PincodeServiceImpl pinService;

	@Autowired
	private PincodeDataRepo pinRepo;

	@GetMapping("/getData")
	public ResponseEntity<List<Pincodes>> getData(@RequestBody Pincodes pincode) throws PinCodeNotFoundException {

		List pincodes = pinService.getAllData(pincode);

		if (!pincodes.isEmpty()) {
			return new ResponseEntity(pincodes, HttpStatus.OK);
		} else {
			throw new PinCodeNotFoundException("Data not found for this pincode");
		}
	}

	@GetMapping("/getDataByCityAndPin")
	public ResponseEntity<List<Pincodes>> getDataByCityAndPin(@RequestBody Pincodes pincode) throws Exception {

		List pincodes = pinService.getAllDataFromCityAndPincode(pincode);

		if (!pincodes.isEmpty()) {
			return new ResponseEntity(pincodes, HttpStatus.OK);
		} else {
			throw new PinCodeNotFoundException("Data not found for this pincode and city");
		}

	}

	@GetMapping("/jsondata")
	public ResponseEntity<?> getJsondata(Pincodes pincode) {

		List<Pincodes> alldata = pinService.getAllDataForJSON();
		// JsonArray array = new JsonArray();

		JsonArray baseArray = new JsonArray();

		for (Pincodes pincodeData : alldata) {
			JsonObject jState = new JsonObject();
			JsonObject jStateData = new JsonObject();
			JsonArray dataArray = new JsonArray();

			// add city and pincode data in innerjson
			jStateData.addProperty("city", pincodeData.getCity());
			jStateData.addProperty("pincode", pincodeData.getMasterPincodesName());
			dataArray.add(jStateData);

			// add state in outerjson and add data in state
			jState.addProperty("State", pincodeData.getState());
			jState.add("Data", dataArray);

			baseArray.add(jState);
		}

		return new ResponseEntity<Object>(baseArray.toString(), HttpStatus.OK);
	}

	@GetMapping("/uniqueStates")
	public ResponseEntity<?> getUniqueStates() {
		List<String> states = pinService.getUniqeStates();
		return new ResponseEntity<Object>(states, HttpStatus.OK);
	}

	@GetMapping("/getDataByState")
	public ResponseEntity<?> getDataByState(Pincodes pincode) {
		List<String> getuniquestates = pinService.getUniqeStates();

		JsonArray baseArray = new JsonArray();
		
		
		

		for (String uniqueStates : getuniquestates) {
			JsonArray arr = new JsonArray();
			JsonObject uniquestatename = new JsonObject();
			uniquestatename.addProperty("state", uniqueStates);
			
			List<Pincodes> states = pinService.getDataByState(uniqueStates);
			
			for (Pincodes pincodes : states) {
				JsonObject jstate = new JsonObject();
				jstate.addProperty("city", pincodes.getCity());
				jstate.addProperty("pincode", pincodes.getMasterPincodesName());
				arr.add(jstate);
				uniquestatename.add("Data", arr);
			}
			baseArray.add(uniquestatename);
		}
		return new ResponseEntity<Object>(baseArray.toString(), HttpStatus.OK);
	}
	
	@GetMapping("/getDataByCity")
	public ResponseEntity<?> getDataByCity(Pincodes pincode){
		
		List<String> cities = pinRepo.findallcity();
		
		JsonArray baseArray=new JsonArray();
		
		for (String city : cities) {
			JsonObject jcity = new JsonObject();
			JsonArray pincodedata = new JsonArray();
			jcity.addProperty("city", city);
			
			List<Integer> citydata = pinService.getDataByCity(city);
			
			for (Integer citypincode : citydata) {
				
				//JsonObject jpincode = new JsonObject();
				
				//jpincode.addProperty("pincode", citypincode.getMasterPincodesName());
				pincodedata.add(citypincode);
				jcity.add("Data", pincodedata);
				
			}
			baseArray.add(jcity);
		}
		return new ResponseEntity<Object>(baseArray.toString(),HttpStatus.OK);
	}
	
	 
	
	@GetMapping("/getDataByStateANDcity")
	public ResponseEntity<?> getDataByStateANDcity(@RequestBody Pincodes pincode){
		
		String city = pincode.getCity();
		String state = pincode.getState();
		
		JsonArray baseArray =new JsonArray();

		List<Integer> allpincode = pinRepo.findmasterPincodesNameBystateAndcity(state,city);
		 
		
		JsonObject data = new JsonObject();
		data.addProperty("state", state);
		data.addProperty("city", city);
		
		JsonArray pincodearray =new JsonArray();
		for (Integer pin : allpincode) {
			pincodearray.add(pin);	
			data.add("pincodes", pincodearray);
		}
		baseArray.add(data);
		
		return new ResponseEntity<Object>(baseArray.toString(),HttpStatus.OK);
	}
	
}
