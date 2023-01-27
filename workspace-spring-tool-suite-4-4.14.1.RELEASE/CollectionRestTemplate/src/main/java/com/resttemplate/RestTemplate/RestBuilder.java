package com.resttemplate.RestTemplate;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.resttemplate.InterceptorOnLog.InterceptorHandler;
import com.resttemplate.InterceptorOnLog.InterceptorLoggerHandler;
import com.resttemplate.filters.RestTemplateFilter;

@Configuration
public class RestBuilder {

	//@Bean
	public RestTemplate restTemp(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMillis(3000))
				.setReadTimeout(Duration.ofMillis(3000)).
				build();
	}
	
	// @Bean
	public RestTemplate Restemp(RestTemplateBuilder builder) {
		
		return builder.
		interceptors(Collections.singletonList(handler())).build();
	}
	
	
	
	
	
	@Bean
	public InterceptorHandler handler() {
		return new InterceptorHandler();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new SCryptPasswordEncoder();
	}
	@Bean
	public RestTemplate rest() {
		ClientHttpRequestFactory factory =new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		RestTemplate template= new RestTemplate(factory);
		template.setInterceptors(Collections.singletonList(handler()));
		return template;
	}
	
}
