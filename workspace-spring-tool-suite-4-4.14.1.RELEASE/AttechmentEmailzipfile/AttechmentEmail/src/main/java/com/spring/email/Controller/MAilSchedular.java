package com.spring.email.Controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.email.emailAttechment.EmailAttechment;
import com.spring.email.emailAttechment.MailsDetails;

@EnableScheduling
@Component
public class MAilSchedular {
	
	@Autowired
	private EmailAttechment attechment;
	

	MailsDetails detail = new MailsDetails();
	
	public void setData(MailsDetails details) {
		
		detail.setSendTo(details.getSendTo());
		detail.setSubject(details.getSubject());
		detail.setMsgbody(details.getMsgbody());
		detail.setColumnName(details.getColumnName());
		detail.setFilename(details.getFilename());
		detail.setTableData(details.getTableData());
	}
	
	@Scheduled(cron = "0 27 19 * * *")
	public void setData1() throws MessagingException, IOException {
		attechment.sendMailWithAttechment(detail);
	}

}
