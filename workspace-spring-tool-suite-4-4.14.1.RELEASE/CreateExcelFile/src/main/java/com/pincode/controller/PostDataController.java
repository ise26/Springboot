package com.pincode.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pincode.entity.PojoDefault;

@RestController
public class PostDataController {

	@PostMapping("/sendData")
	public Object sendData(@RequestBody PojoDefault data) throws IOException {
		String filename = data.getFileName();
		JSONObject jobject = new JSONObject();
		jobject.put("filename", filename);
		jobject.put("column name", data.getColumnNames());
		jobject.put("data", data.getData());

		String file = "C:\\Users\\Shubham.Ise\\Desktop\\" + filename + ".xlsx";

		// create instance of workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// creating sheet
		HSSFSheet sheet = workbook.createSheet((String) jobject.get("filename"));

		// creating header row
		HSSFRow headRow = sheet.createRow(0);

		List<String> columnnames = (List<String>) jobject.get("column name");

		for (int i = 0; i < columnnames.size(); i++) {
			headRow.createCell(i).setCellValue(columnnames.get(i));
		}

		// creating data rows

		// ---------------By using list and map-----------------
//		List<Map<String, Object>> allData = (List<Map<String, Object>>) jobject.get("data");
//		// System.out.println(allData);
//		// Map<String, Object> map=(Map<String, Object>) allData;
//
//		List<Object> allvalues = new ArrayList<Object>();
//		for (Map<String, Object> map : allData) {
//
//			System.out.println("iterated data: " + map.values());
//			allvalues.add(map.values());
//			System.out.println("list data: " + allvalues);
//			
//			
//			for (int i = 0; i < allvalues.size(); i++) {
//				String temp = allvalues.get(i).toString();
//				System.out.println("String value in list: " + temp);
//				dataRow.createCell(i).setCellValue((String) temp);
//
//			}
//		}

		// --------------------------------//---------------------------------------//--------------------
		// List<Map<String, Object>> allData = (List<Map<String, Object>>)
		// jobject.get("data");
		// List<Object> allData = (List<Object>) jobject.get("data");

//		for (Iterator iterator = data.getData().iterator(); iterator.hasNext();) {
//			Map<String, String> string = (Map<String, String>) iterator.next();
//			
//			
//			//System.out.println(string);
//			
//			for (String s : string.keySet()) {
//				String obj=string.get(s);
//				System.out.println(obj);
//				HSSFRow dataRow = sheet.createRow(1);
//				dataRow.createCell(0).setCellValue(obj);
//				
//			}

//		}

		// --------------------//////////////========-/--------------------------------------
		ArrayList<String> alist = new ArrayList<String>();
		List<Map<String, Object>> allData = (List<Map<String, Object>>) jobject.get("data");

		for (Map<String, Object> map : allData) {
			Set<String> setString = map.keySet();
			Iterator<String> itr = setString.iterator();

			while (itr.hasNext()) {

				String s = itr.next();
				alist.add((String) map.get(s));
				System.out.println(map.get(s));

			}
		}
		System.out.println(alist);
		List<List<String>> outputlist = ListUtils.partition(alist, columnnames.size());
		System.out.println("partition list:" + outputlist);
		for (int k = 0; k < outputlist.size(); k++) {
			System.out.println(outputlist.size());
			System.out.println("current value of i " + k);
			HSSFRow datarow = sheet.createRow(k + 1);
			System.out.println(k + 1 + "row value");

			for (int j = 0; j < columnnames.size(); j++) {
				System.out.println(outputlist.get(k));
				System.out.println(outputlist.get(k).get(j));
				datarow.createCell(j).setCellValue(outputlist.get(k).get(j));
			}
		}

		try {
			FileOutputStream output = new FileOutputStream(file);
			workbook.write(output);
			System.out.println("Excel file has been generated successfully.");

			output.close();
			workbook.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return jobject;
	}

	@PostMapping("/senddatalist")
	public Object sendDatalist(@RequestBody PojoDefault data) throws IOException {

		String filename = data.getFileName();
		JSONObject jobject = new JSONObject();
		jobject.put("filename", filename);
		jobject.put("columnname", data.getColumnNames());
		jobject.put("data", data.getData1());
		System.out.println(jobject);
		String file = "C:\\Users\\Shubham.Ise\\Desktop\\" + filename + ".xlsx";

		// create instance of workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// creating sheet
		HSSFSheet sheet = workbook.createSheet((String) jobject.get("filename"));

		// creating header row
		HSSFRow headRow = sheet.createRow(0);

		List<String> columnnames = (List<String>) jobject.get("columnname");

		for (int i = 0; i < columnnames.size(); i++) {
			headRow.createCell(i).setCellValue(columnnames.get(i));
		}

		List<List<String>> listdata = (List<List<String>>) jobject.get("data");
		for (int i = 0; i < listdata.size(); i++) {
			System.out.println(listdata.size());
			System.out.println("current value of i " + i);
			HSSFRow datarow = sheet.createRow(i + 1);
		

			for (int j = 0; j < columnnames.size(); j++) {
				System.out.println(listdata.get(i));
				System.out.println(listdata.get(i).get(j));
				datarow.createCell(j).setCellValue(listdata.get(i).get(j));
			}
		}

		try {
			FileOutputStream output = new FileOutputStream(file);
			workbook.write(output);
			System.out.println("Excel file has been generated successfully.");

			output.close();
			workbook.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return jobject;
	}
	
}
