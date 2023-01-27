package com.emailexcelfileScheduler.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

	private String sendTo;
	private String subject;
	private String emailbody;
	
	private String filename;
	private List<List<String>> tableData;
	private List<String> columnName;
}
