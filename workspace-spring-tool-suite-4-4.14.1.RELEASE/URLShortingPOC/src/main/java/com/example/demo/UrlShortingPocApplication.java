package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = "com.example.demo.repository")
@OpenAPIDefinition(info = @Info(title = "UrlShortingPocApplication", version = "2.0", description = "UrlShortingPocApplication"))
public class UrlShortingPocApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(UrlShortingPocApplication.class, args);

		RestTemplate template = new RestTemplate();
		String str = template.getForObject("http://localhost:8080/v3/api-docs", String.class);
		FileWriter file = new FileWriter("D:/UrlShortingPocApplication.json");

		file.write(str);
		file.close();
	}

}
