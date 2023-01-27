package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ErrorResponseModel;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.Url;
import com.example.demo.model.UrlDto;
import com.example.demo.service.UrlService;

@RestController
public class UrlShortingController {

	@Autowired
	UrlService urlService;

	@Autowired
	HttpServletResponse response;

	@PostMapping("/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto) {

		Url urlRet = urlService.generateShortLink(urlDto);

		if (urlRet != null) {
			ResponseModel urlResponse = new ResponseModel();
			urlResponse.setExpirationDate(urlRet.getCreationDate());
			urlResponse.setOriginalUrl(urlRet.getOriginalUrl());
			urlResponse.setShortLink(urlRet.getShortLink());

			return new ResponseEntity<ResponseModel>(urlResponse, HttpStatus.OK);
		}
		ErrorResponseModel errorModel = new ErrorResponseModel();
		errorModel.setErrorCode("400!!");
		errorModel.setMessage("bad values!!!!!");

		return new ResponseEntity<ErrorResponseModel>(errorModel, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectOginalUrl(@PathVariable String shortLink) throws IOException {

		if (StringUtils.isEmpty(shortLink)) {
			ErrorResponseModel errorModel = new ErrorResponseModel();
			errorModel.setErrorCode("400");
			errorModel.setMessage("bad values!!!!!");

			return new ResponseEntity<ErrorResponseModel>(errorModel, HttpStatus.BAD_REQUEST);

		}

		Url urlReslt = urlService.encodedShortLink(shortLink);

		if (urlReslt.getExpiration().isBefore(LocalDateTime.now())) {

			ErrorResponseModel errorModel = new ErrorResponseModel();
			errorModel.setErrorCode("200");
			errorModel.setMessage("URL Expired. Please try generation fresh one.");

			return new ResponseEntity<ErrorResponseModel>(errorModel, HttpStatus.BAD_REQUEST);
		}

		response.sendRedirect(urlReslt.getOriginalUrl());
		return null;

	}
}
