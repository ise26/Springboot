 package com.bitlyUrlShort.controller;

import java.awt.PageAttributes.MediaType;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bitlyUrlShort.Entity.Entity;
import com.bitlyUrlShort.Entity.ExpandUrlPojo;
import com.bitlyUrlShort.filter.BitlyFilter;
import com.bitlyUrlShort.service.BitlyService;

@RestController
public class BitlyController {

	@Autowired
	private BitlyService service;

	@Autowired
	private BitlyFilter filter;

	
	
	RestTemplate temp = new RestTemplate();

	@PostMapping("/shorten")
	public ResponseEntity<String> getUrl(@RequestBody Entity data, HttpServletRequest request)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		
		String decryptedToken =(String) request.getAttribute("decryptedToken");
		System.out.println("Decrypted token in controller is: " + decryptedToken);

		String url = "https://api-ssl.bitly.com/v4/shorten";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		header.set("Authorization","Bearer "+decryptedToken);
		HttpEntity entity = new HttpEntity(data, header);

		return temp.exchange(url, HttpMethod.POST, entity, String.class);
	}

	@PostMapping("/expand")
	public ResponseEntity<String> expandUrl(@RequestBody ExpandUrlPojo data, HttpServletRequest request) {
		
		//decrypt token
		String decryptedToken=(String) request.getAttribute("decryptedToken");
		
		
		String url = "https://api-ssl.bitly.com/v4/expand";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		header.set("Authorization", "Bearer "+decryptedToken);
		HttpEntity entity = new HttpEntity(data, header);

		return temp.exchange(url, HttpMethod.POST, entity, String.class);
	}

	@GetMapping("generateKey")
	public String genKey() throws NoSuchAlgorithmException {
		return service.generatekey();

	}

	@PostMapping("encryptToken")
	public String encryptToken(@RequestBody String token) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		return service.encodeToken(token);
	}

	@PostMapping("decryptToken")
	public String decryptToken(@RequestBody String token) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		return service.decodeToken(token);
	}
	
}
