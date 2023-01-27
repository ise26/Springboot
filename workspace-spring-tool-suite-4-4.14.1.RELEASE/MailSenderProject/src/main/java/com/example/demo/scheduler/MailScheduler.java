package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.MailEntity;
import com.example.demo.service.SimpleMail;

@Component
public class MailScheduler {

	@Autowired
	private SimpleMail sendmail;
	
}
