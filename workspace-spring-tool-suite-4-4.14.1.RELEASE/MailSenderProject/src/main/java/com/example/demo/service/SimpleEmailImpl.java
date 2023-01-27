package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MailEntity;
@Service
public class SimpleEmailImpl implements SimpleMail {
	
	@Autowired
	private MailSender mailsender;
	
	@Value("{spring.mail.username}")
	String user;
	
	@Override
	public String sendSimpleEmail(MailEntity mail) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setFrom(user);
		sm.setTo(mail.getRecipient());
		sm.setText(mail.getMsgBody());
		sm.setSubject(mail.getSubject());
		
		mailsender.send(sm);
		
		
		return "mail send successfully";

	}

}
