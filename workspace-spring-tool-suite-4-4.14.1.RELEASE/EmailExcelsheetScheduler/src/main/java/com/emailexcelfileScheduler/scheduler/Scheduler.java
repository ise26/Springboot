package com.emailexcelfileScheduler.scheduler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.emailexcelfileScheduler.model.EmailDto;
import com.emailexcelfileScheduler.service.MailService;

@Component
public class Scheduler {

	@Autowired
	private MailService service;
	
	
	
	
	EmailDto model= new EmailDto();
	
	public void setData(EmailDto dto) {
		
		
		model.setSubject(dto.getSubject());
		model.setSendTo(dto.getSendTo());
		model.setEmailbody(dto.getEmailbody());
		model.setColumnName(dto.getColumnName());
		model.setTableData(dto.getTableData());
		model.setFilename(dto.getFilename());
		
		
		
	}
	
	@Scheduled(cron = "0 01 13  * * *")
	public void sendMail() throws MessagingException, IOException {
		service.sendMail(model);
	}
}
