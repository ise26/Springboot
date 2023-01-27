package com.example.demo.rest.controller;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.rest.entity.InterceptEntity;
import com.example.demo.rest.exception.NoMoreDataException;
import com.example.demo.rest.model.AadhaarSubmit;
import com.example.demo.rest.model.Rests;
import com.example.demo.rest.repository.InterceptRepository;
import com.example.demo.rest.service.EncryptDecryptService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@Validated
public class AllController {
	
	Logger logg = Logger.getLogger("");
	
	@Autowired
	private InterceptRepository interceptRepository;
	
	@Autowired
	private EncryptDecryptService encryptDecryptService;
	
	@Value("${ExternalApi}")
	private String externalApi;
	
	@Value("${pan}")
	private String pan;
	
	@Value("${license}")
	private String license;
	
	@Value("${voter}")
	private String voter;
	
	@Value("${aadhar}")
	private String aadhar;
	
	@Value("${aadharotp}")
	private String aadharOtp;
	
	@Value("${submitotp}")
	private String submitOtp;
	
	HttpServletRequest request;
	
	@Autowired
	RestTemplate restTemplate;

	
	@PostMapping(value = "/pan/validate")
	public ResponseEntity<String> getPan(@RequestBody Rests val, HttpSession session)
	{
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
		HttpEntity<String> entity = new HttpEntity(val, header);
        return  restTemplate.exchange(pan, HttpMethod.POST,entity,String.class);
	}
	
	@PostMapping(value="/license/validate")
	public ResponseEntity<String> getLicense(@RequestBody Rests val)
	{
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
		HttpEntity<String> entity = new HttpEntity(val, header);
		return restTemplate.exchange(license, HttpMethod.POST,entity,String.class);
	}
	
	@PostMapping(value="/voter/validate")
	public ResponseEntity<String> getVoter(@RequestBody Rests val) throws NoMoreDataException
	{
		if(val.getId_number() != null)
		{
			HttpHeaders header = new HttpHeaders();
			header.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
			HttpEntity<String> entity = new HttpEntity(val, header);
			return restTemplate.exchange(voter, HttpMethod.POST,entity,String.class);
		}
		else
		{
			throw new NoMoreDataException("ID SHOULD NOT BE NULL");
		}
	}
	
	@PostMapping(value="/aadhaar/validate")
	public ResponseEntity<String> getAdhaar(@RequestBody Rests val) throws NoMoreDataException
	{
		System.out.println("Hii i am a controller ");
		String regValue = val.getId_number();
		String condition = "[0-9]+";
		boolean result = Pattern.matches(condition, regValue);
		if(val.getId_number() != null && result)
		{
			HttpHeaders header = new HttpHeaders();
			header.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
			HttpEntity<String> entity = new HttpEntity(val, header);
			return restTemplate.exchange(aadhar, HttpMethod.POST,entity,String.class);
		}
		else
		{
			throw new NoMoreDataException("ID should not be null or check your id");
		}
	}
	
	@PostMapping(value="/aadhaarotp/validate")
	public ResponseEntity<String> getAdhaarOtp(@RequestBody Rests val)
	{
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
		HttpEntity<String> entity = new HttpEntity(val, header);
		    ResponseEntity<String> responseEntity = restTemplate.exchange(aadharOtp, HttpMethod.POST,entity,String.class);
			return responseEntity;
	}
	
	@PostMapping(value="/aadhaarotpsubmit/validate")
	public ResponseEntity<String> AdhaarOtpSubmit(@RequestBody AadhaarSubmit val)
	{
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
		HttpEntity<String> entity = new HttpEntity(val, header);
		ResponseEntity<String> responseEntity = restTemplate.exchange(submitOtp, HttpMethod.POST,entity,String.class);
		return responseEntity;
	}
}
