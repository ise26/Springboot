package com.emailexcelfileScheduler.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emailexcelfileScheduler.model.EmailDto;
import com.emailexcelfileScheduler.scheduler.Scheduler;
import com.emailexcelfileScheduler.service.MailService;

@RestController
public class MainController {

	@Autowired
	private MailService service;
	
	@Autowired
	private Scheduler scheduler;
	
	
	@PostMapping("/send")
	public String sendMail(@RequestBody EmailDto dto) throws MessagingException, IOException {
	return service.sendMail(dto);
	}
	
	@GetMapping("/send")
	public void sendschedulemail() throws MessagingException {
	service.schedulemail();
	}
	

	@PostMapping("/send1")
	public void sendschedul(@RequestBody EmailDto dto) throws MessagingException {
	scheduler.setData(dto);
	}
}
