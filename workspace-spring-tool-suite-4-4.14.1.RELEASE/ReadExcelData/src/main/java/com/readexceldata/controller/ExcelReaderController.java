package com.readexceldata.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.readexceldata.entity.Employee;

@RestController
public class ExcelReaderController {

	@RequestMapping(value = "Book1", method = RequestMethod.POST)
	public ResponseEntity<List<Employee>> excelReader(@RequestParam("file") MultipartFile files) throws IOException {

		List<Employee> li = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);

		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			if (i > 0) {
				Employee e = new Employee();
				XSSFRow row = sheet.getRow(i);
				Integer j = (int) row.getCell(0).getNumericCellValue();
				e.setId(j.toString());
				e.setName(row.getCell(1).getStringCellValue());
				e.setAge(row.getCell(2).getNumericCellValue());
				e.setCity(row.getCell(3).getStringCellValue());
				li.add(e);	
			}
		}
		List li3=li.stream().map(i -> i.getCity()).collect(Collectors.toList());
		li3.stream().forEach(i-> System.out.println(i));
		return new ResponseEntity<List<Employee>>(li, HttpStatus.OK);
	}

}
