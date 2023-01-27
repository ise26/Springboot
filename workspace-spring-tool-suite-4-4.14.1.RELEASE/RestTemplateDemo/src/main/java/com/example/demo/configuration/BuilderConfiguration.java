package com.example.demo.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class BuilderConfiguration {
	
	@Bean
	public RestTemplate getRestTemp(RestTemplateBuilder builder) {
		return builder.build();
	}
}
