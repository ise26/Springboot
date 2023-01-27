package com.pincode.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pincode.entity.Customers;
import com.pincode.excelService.ExcelGenerator;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.Resolution;

@Controller
public class ExcelFileController {

	@GetMapping("/index")
    public String index() {
        return "index";
    }

	private List<Customers> addData(){
		
		List<Customers> customers = new ArrayList<Customers>();
		customers.add(new Customers("shubham", "ise","asdfghjk@gmail.com", "0987989899"));
		customers.add(new Customers("shubham", "sdfghnj","asdfghjk@gmail.com", "0987989899"));

	
		return customers;
	}
	
	@GetMapping("/download/customers.xlsx")
	public void downloadCSV(HttpServletResponse response) throws Exception{
		response.setContentType("application/octect-stream");
		response.setHeader("Content-Disposition", "attachment;filename=customers.xlsx");
		ByteArrayInputStream stream = ExcelGenerator.createExcel((addData())) ;
		IOUtils.copy(stream, response.getOutputStream());
	}
}