package com.spring.email.emailAttechment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailAttechment {
	
	@Autowired
	private JavaMailSender mailSender;

	@Value("{spring.mail.username}")
	String user;
	
	
	public void sendMailWithAttechment(MailsDetails mails) throws MessagingException, IOException {
		
		 MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, true);
         helper.setFrom(user);
         helper.setTo(mails.getSendTo());
        helper.setText(mails.getMsgbody());
     
       
        HSSFWorkbook hssf = new HSSFWorkbook();
        HSSFSheet hfsheet = hssf.createSheet("TotalData");
        HSSFRow hsfRow = hfsheet.createRow((short)0);
        String file = mails.getFilename();
        String filename = file + ".xlsx";
        System.out.println(filename);
        
        List<String> columnName = mails.getColumnName();
        System.out.println("gjhkjjlkkl----------+++++++++++"+columnName);
        for (int i = 0; i < columnName.size(); i++) {
        	System.out.println("hii");
			hsfRow.createCell(i).setCellValue(columnName.get(i));
			
		} 
        List<List<String>> datarow = mails.getTableData();
    	for (int j = 0; j < datarow.size(); j++) {
			System.out.println(datarow);
			HSSFRow data = hfsheet.createRow(j);
			for (int k = 0; k < columnName.size(); k++) {
			
				data.createCell(k).setCellValue(datarow.get(j).get(k));
			}
		}
    	
    	FileOutputStream fileout = new FileOutputStream(filename);
    	hssf.write(fileout);
    	fileout.close();
    	hssf.close();
    	 FileSystemResource file1 = new FileSystemResource(filename);
       helper.addAttachment("testfile", file1);
        
         helper.setSubject(mails.getSubject());
         mailSender.send(message);
         System.out.println("mailed successfully");
	}

	
}
