package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productdto {

	private Long pid;
	private String name;
	private String price;
	private String image;
	private String description;
	private String color;
	private String size;
	private Long category_id;
}
