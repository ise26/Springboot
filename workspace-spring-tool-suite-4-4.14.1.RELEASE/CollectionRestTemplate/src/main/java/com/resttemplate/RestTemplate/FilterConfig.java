package com.resttemplate.RestTemplate;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import com.resttemplate.filters.RestTemplateFilter;

public class FilterConfig {
	@Bean
	public FilterRegistrationBean<RestTemplateFilter> filterRegisterBean(){
		FilterRegistrationBean<RestTemplateFilter> registerFilter=new FilterRegistrationBean<>();
		RestTemplateFilter filter=new RestTemplateFilter();
		registerFilter.setFilter(filter);
		registerFilter.addUrlPatterns("/*");
		return registerFilter;
		
	}
	
	
}
