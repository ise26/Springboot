package com.spring.email.emailAttechment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailsDetails {
	
	private String sendTo;
	private String msgbody;
	private String subject;
	private String filename;
	private List<List<String>> tableData;
	private List<String> columnName;
	
	

}
