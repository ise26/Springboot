package com.pincode.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PojoDefault {
	private String fileName;
	private Object columnNames;
	private Object data;
	private Object data1;
}
