package com.spring.email.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.email.emailAttechment.MailsDetails;

@RestController
public class EmailAttechmentController {
	
	@Autowired
	private MAilSchedular schedular;
	 
		@PostMapping("/sendallData")
	    public void sendMail(@RequestBody  MailsDetails details)
	    {
			 schedular.setData(details);
	        
	    }
		
		
}
