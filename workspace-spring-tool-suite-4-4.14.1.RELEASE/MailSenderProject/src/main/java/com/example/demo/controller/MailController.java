package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MailEntity;
import com.example.demo.service.SimpleMail;


@RestController
public class MailController {
	
	@Autowired
	private SimpleMail mailservice;
	
	@PostMapping("/send")
//	@Scheduled(cron = "0 41 17 * * *")
	public ResponseEntity<String> sendMail(@RequestBody MailEntity mail){
		return new ResponseEntity<String>(mailservice.sendSimpleEmail(mail),HttpStatus.OK);
	}
}
