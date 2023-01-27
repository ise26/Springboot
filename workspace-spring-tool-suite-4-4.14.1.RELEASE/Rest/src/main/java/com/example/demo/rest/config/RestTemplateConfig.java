package com.example.demo.rest.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.example.demo.rest.advice.LoggingInterceptor;

@Configuration
public class RestTemplateConfig {

	@Bean
	 @Qualifier(value = "withLogger")
	public RestTemplate restTemplate() {
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setInterceptors(Collections.singletonList(getloggingInterceptor()));
		return restTemplate;
	}

	
//	  @Bean
//	  
//	  @Qualifier(value = "timer") public RestTemplate
//	  restTemplate(RestTemplateBuilder builder) { return
//	  builder.setConnectTimeout(Duration.ofMillis(30000)).setReadTimeout(Duration.
//	  ofMillis(30000)).build(); }
	 
	@Bean
	public LoggingInterceptor getloggingInterceptor() {
		return new LoggingInterceptor();
	}



	

}
