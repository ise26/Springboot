package com.urlshortner.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.google.common.hash.Hashing;
import com.urlshortner.dto.UrlDto;
import com.urlshortner.service.UrlService;

@Controller
public class UrlController {
	
	@Autowired
	private UrlService service;
	
	
	Map<String, String> map=new HashMap<String, String>();
	@PostMapping("/create")
	public ResponseEntity createUrl(@RequestBody UrlDto url){
		
//		String urlid=Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
//		map.put(urlid, url);
//		System.out.println(urlid);
		
		String createdurl= service.createUrl(url.getUrl());
		
		return ResponseEntity.ok(createdurl);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<List> getUrl(@PathVariable String id) {
//		String urlid=map.get(id);
		List li=service.getUrl(id);
		return new ResponseEntity<List>(li, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/{url}")
	public ResponseEntity<?> getOriginalUrl(@PathVariable String url, HttpServletResponse response) throws IOException {
		String ogUrl=service.getOgUrl(url);
		System.out.println(ogUrl);
		response.sendRedirect(ogUrl);
		return null;
		
	}
}
