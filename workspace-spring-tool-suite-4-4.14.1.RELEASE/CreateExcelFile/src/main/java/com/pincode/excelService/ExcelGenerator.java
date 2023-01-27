package com.pincode.excelService;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.pincode.entity.Customers;

public class ExcelGenerator {

	public static ByteArrayInputStream createExcel(List<Customers> customers) throws Exception {
		
		try(Workbook workbook= new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("customers");
			
			Row row = sheet.createRow(0);
			CellStyle headerstyle=workbook.createCellStyle();
			headerstyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			headerstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			
			//set cell
			Cell cell =row.createCell(0);
			cell.setCellValue("firstName");
			cell.setCellStyle(headerstyle);
			
			cell =row.createCell(1);
			cell.setCellValue("lastName");
			cell.setCellStyle(headerstyle);
			
			cell =row.createCell(2);
			cell.setCellValue("email");
			cell.setCellStyle(headerstyle);
			
			cell =row.createCell(3);
			cell.setCellValue("phone");
			cell.setCellStyle(headerstyle);
			
			//creating data rows
			for(int i=0; i<customers.size();i++) {
				Row dataRow= sheet.createRow(i+1);
				
				dataRow.createCell(0).setCellValue(customers.get(i).getFirstName());
				dataRow.createCell(1).setCellValue(customers.get(i).getLastName());
				dataRow.createCell(2).setCellValue(customers.get(i).getEmail());
				dataRow.createCell(3).setCellValue(customers.get(i).getPhone());
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		}catch (Exception e) {
			e.getMessage();
			return null;
		}
		
		
	}
	
}
