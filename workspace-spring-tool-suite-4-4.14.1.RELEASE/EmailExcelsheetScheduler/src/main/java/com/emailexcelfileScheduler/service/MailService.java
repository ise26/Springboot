package com.emailexcelfileScheduler.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emailexcelfileScheduler.model.EmailDto;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("{spring.mail.user}")
	String usermail;
	
	//send mail with model object
	//@Scheduled(cron="0 43 18 * * *")  //we canot use  scheduled annotation with argument method this is not working because it has parameters.
	public String sendMail(EmailDto dto) throws MessagingException, IOException {
		
		MimeMessage message= mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		
		
		//create excel file
		
		HSSFWorkbook workbook= new HSSFWorkbook();
		HSSFSheet worksheet=workbook.createSheet("Excel Data");
		HSSFRow rows=worksheet.createRow(0);
		
		String filename=dto.getFilename()+".xlsx";
		
		//create column with name
		List<String> columnnames=dto.getColumnName();
		System.out.println("columns "+columnnames);
		 for (int i = 0; i < columnnames.size(); i++) {
			rows.createCell(i).setCellValue(columnnames.get(i));
			System.out.print(columnnames.get(i) +" ");
		}
		
		List<List<String>> tableData=dto.getTableData();
		for (int i = 0; i < tableData.size(); i++) {
			HSSFRow dataRow=worksheet.createRow(i);
			
			for (int j = 0; j < columnnames.size(); j++) {
				dataRow.createCell(j).setCellValue(tableData.get(i).get(j));
				System.out.println(tableData.get(i).get(j));
			}
		}
		
		//write data in file
		FileOutputStream fileout = new FileOutputStream(filename);
    	workbook.write(fileout);
    	fileout.close();
    	workbook.close();
		
		helper.setTo(dto.getSendTo());
		helper.setFrom(usermail);
		helper.setSubject(dto.getSubject());
		helper.setText(dto.getEmailbody());
		FileSystemResource excelfile= new FileSystemResource(filename);
		helper.addAttachment("file", excelfile);
		mailSender.send(message);
		return "mailed successfully";
	}
	
	
	
	
	// send mail with hardcoded data
	@Scheduled(cron = "0 40 18 * * *")
	public void schedulemail() throws MessagingException {
		MimeMessage message= mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		
		helper.setTo("iseshubham26@gmail.com");
		helper.setFrom(usermail);
		helper.setSubject("this is excel file");
		helper.setText("download this file");
		FileSystemResource excelfile= new FileSystemResource("C:\\Users\\Shubham.Ise\\Desktop\\Credit_Cards_PIN_Code___Location_Checklist_v4.0f (1).xlsx");
		helper.addAttachment("file", excelfile);
		
		mailSender.send(message);
	
		System.out.println("scheduler worked");
	}
	
	// send mail with schecduler class setting data in global variables
	//if you want to send data from request body/parameters in method:
	//1) make one class scheduler 
	//2)create global variables
	//3)take data from requestbody and send to one method 
	//4)get data from controller in method and set to global varibales,make scheduled method in this method pass service method.for that  and make argumented method in service and  use global variables in it.

	
	
	public String send(EmailDto dto) throws MessagingException {
		
		MimeMessage message= mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		
		helper.setTo(dto.getSendTo());
		helper.setFrom(usermail);
		helper.setSubject(dto.getSubject());
		helper.setText(dto.getEmailbody());
		FileSystemResource excelfile= new FileSystemResource("C:\\Users\\Shubham.Ise\\Desktop\\Credit_Cards_PIN_Code___Location_Checklist_v4.0f (1).xlsx");
		helper.addAttachment("file", excelfile);
		
		mailSender.send(message);
		return "mailed successfully";
	}
	
}
