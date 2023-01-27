package com.bitlyUrlShort.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlyUrlShort.BitlyEntity;
import com.bitlyUrlShort.service.BitlyService;

@RestController
public class BitlyController {
	
	@Autowired
	private BitlyService service;
	
	@PostMapping("/getbitlyurl")
	public String getBitlyUrl(@RequestBody BitlyEntity data) throws UnsupportedEncodingException {
		
		String url=data.getOriginalUrl();

		return service.shortUrl(url);
	}
	
	@PostMapping("/getogurl")
	public String getOgUrl(@RequestBody BitlyEntity data) throws UnsupportedEncodingException {
		
		String url=data.getOriginalUrl();

		return service.expand(url);
	}
}
