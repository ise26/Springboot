package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SetSystemPropertyProjectApplication {

	public static void main(String[] args) {
		//System.setProperty("id", "dfghj");
		SpringApplication.run(SetSystemPropertyProjectApplication.class, args);
	}

}
