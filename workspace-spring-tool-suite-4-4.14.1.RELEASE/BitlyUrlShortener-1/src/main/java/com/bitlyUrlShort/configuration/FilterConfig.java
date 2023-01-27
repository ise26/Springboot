package com.bitlyUrlShort.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bitlyUrlShort.filter.BitlyFilter;


public class FilterConfig {

	@Bean
	FilterRegistrationBean<BitlyFilter> filterBean(){
		FilterRegistrationBean<BitlyFilter> filterObj= new FilterRegistrationBean<>();
		
		BitlyFilter bitlyFilter = new BitlyFilter();
		filterObj.setFilter(bitlyFilter);
		filterObj.addUrlPatterns("/*");
		
		return filterObj;
		
	}
}
