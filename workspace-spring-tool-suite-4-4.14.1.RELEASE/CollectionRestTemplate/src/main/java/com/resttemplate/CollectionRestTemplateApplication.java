package com.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.resttemplate.repository")

public class CollectionRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectionRestTemplateApplication.class, args);
	}

}
