package com.resttemplate.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.resttemplate.Entity.LoggerData;
import com.resttemplate.Entity.ModelClass;
import com.resttemplate.Entity.ModelClass2;
import com.resttemplate.Exxeption.NoDataFoundException;
import com.resttemplate.service.ResttemplateServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private RestTemplate restRemp;

	@Autowired
	private ResttemplateServiceImpl service;

	@Value("${panurl}")
	private String panurl;

	@Value("${adharurl}")
	private String adharurl;

	@Value("${licence}")
	private String licence;

	@Value("${voterid}")
	private String voterid;

	@Value("${generateotp}")
	private String generateotp;

	@Value("${submitotp}")
	private String submitotp;

	@Value("${commonurl}")
	private String url1;

	@PostMapping("/getsecretkey")
	public void getKeys() throws NoSuchAlgorithmException {
		service.generateSecretKey();
	}

	@PostMapping("/encrypt")
	public String encryptData(@RequestBody String data) throws InvalidKeyException, NoSuchPaddingException,
			NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		return service.encryptData(data);

	}

	@PostMapping("/decrypt")
	public String decryptData(@RequestBody String data) throws InvalidKeyException, NoSuchPaddingException,
			NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		return service.decryptData(data);
	}

	@PostMapping("/panverify")
	public ResponseEntity<String> panVerify(@Valid @RequestBody ModelClass model) {

//		String verifyPan=model.getId_number();
//		if(verifyPan.matches("")) {}
		// && model.getId_number().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}"
		String idnumber = model.getId_number();
		if (model.getId_number() != null) {

			try {
				HttpHeaders header = new HttpHeaders();
				header.set("Authorization",
						"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
				HttpEntity<String> entity = new HttpEntity(model, header);
				ResponseEntity<String> response = restRemp.exchange(url1 + panurl, HttpMethod.POST, entity,
						String.class);

				// add data to database
//				try {
//			LoggerData data=new LoggerData();
//				
//				data.setRequest(idnumber);
//				data.setRequesttimestamp(new Date().toString());
//				data.setResponse(response.getBody());
//				data.setResponsetimestamp(new Date().toString());
//				
//				repo.save(data);
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
				return response;

			} catch (Exception e) {

				throw new NoDataFoundException("Please give proper pan number");

			}

		} else {
			throw new NoDataFoundException("Please enter pan number");
		}

	}

	@PostMapping("/voteridverify")
	public ResponseEntity<String> voterid(@RequestBody ModelClass model) {
		if (model.getId_number() != null) {

			try {
				HttpHeaders header = new HttpHeaders();
				header.set("Authorization",
						"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
				HttpEntity<String> entity = new HttpEntity(model, header);
				return restRemp.exchange(url1 + voterid, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {

				throw new NoDataFoundException("Please give proper id number");

			}

		} else {
			throw new NoDataFoundException("Please enter id number");
		}
	}

	@PostMapping("/licence")
	public ResponseEntity<String> licenceVerify(@RequestBody ModelClass model) {
		if (model.getId_number() != null) {

			try {
				HttpHeaders header = new HttpHeaders();
				header.set("Authorization",
						"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
				HttpEntity<String> entity = new HttpEntity(model, header);
				return restRemp.exchange(url1 + licence, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {

				throw new NoDataFoundException("Please give proper id number");

			}

		} else {
			throw new NoDataFoundException("Please enter id number");
		}
	}

	@PostMapping("/adharValidation")
	public ResponseEntity<String> adharValidation(@RequestBody ModelClass model) {
		if (model.getId_number() != null) {

			try {
				HttpHeaders header = new HttpHeaders();
				header.set("Authorization",
						"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
				HttpEntity<String> entity = new HttpEntity(model, header);
				return restRemp.exchange(url1 + adharurl, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {

				throw new NoDataFoundException("Please give proper id number");

			}

		} else {
			throw new NoDataFoundException("Please enter id number");
		}
	}

	@PostMapping("/generateOTP")
	public ResponseEntity<String> generateOTP(@RequestBody ModelClass model) {
		if (model.getId_number() != null) {

			try {
				HttpHeaders header = new HttpHeaders();
				header.set("Authorization",
						"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
				HttpEntity<String> entity = new HttpEntity(model, header);
				return restRemp.exchange(url1 + generateotp, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {

				throw new NoDataFoundException("Please give proper id number");

			}

		} else {
			throw new NoDataFoundException("Please enter id number");
		}
	}

	@PostMapping("/submitOTP")
	public ResponseEntity<String> submitOTP(@RequestBody ModelClass2 model) {
		if (model.getClient_id() != null) {

			try {
				HttpHeaders header = new HttpHeaders();
				header.set("Authorization",
						"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MjcwNDE5MywianRpIjoiYWQ5ZWNkYTItNDY0Mi00MGY3LTgxOTEtNjc5MmZjZjQ0MmNmIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnRla25vcG9pbnRAc3VyZXBhc3MuaW8iLCJuYmYiOjE2NjI3MDQxOTMsImV4cCI6MTY2NTI5NjE5MywidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbIndhbGxldCJdfX0.Ow5gV6Di7vddH_LwWlWXhT8bRhUH7dGCxPf_G4tENHI");
				HttpEntity<String> entity = new HttpEntity(model, header);
				return restRemp.exchange(url1 + submitotp, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {

				throw new NoDataFoundException("Please give proper id number");

			}

		} else {
			throw new NoDataFoundException("Please enter id number");
		}
	}

	@GetMapping("/demoapicall")
	public ResponseEntity<List> getData(){
			RestTemplate resttemp=new RestTemplate();
			ResponseEntity<List> response= resttemp.getForEntity("https://jsonplaceholder.typicode.com/users", List.class);
			return response;
			}

}
