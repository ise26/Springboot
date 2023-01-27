package com.test.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/data")
public class TestController {

	@Autowired
	Environment env;

	@GetMapping("/getenv")
	public String getApplicatonPropertydata() throws IOException {
		return env.getProperty("certificate");
	}

	@GetMapping("/getEndpoint")
	public String TestData(@RequestParam String url) throws IOException {

		String endpoint = url.substring(url.lastIndexOf("/")).substring(1);
		System.out.println(endpoint);
		Properties properties = new Properties();
		FileOutputStream outStream = new FileOutputStream(
				"C:\\Users\\Shubham.Ise\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\SetSystemPropertyProject\\src\\main\\resources\\application.properties",
				true);

		if (env.containsProperty(endpoint)) {
			return "this url is already used";
		} else {
			properties.setProperty(endpoint, url);
			properties.store(outStream, "");
			return "Done";
		}
	}
}
