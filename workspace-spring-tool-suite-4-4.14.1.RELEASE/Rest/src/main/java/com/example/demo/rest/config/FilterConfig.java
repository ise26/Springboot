package com.example.demo.rest.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.rest.filter.MyFilter;

public class FilterConfig {

	@Bean
	public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
		FilterRegistrationBean<MyFilter> filterRegister = new FilterRegistrationBean<>();
		MyFilter myFilter = new MyFilter();
		filterRegister.setFilter(myFilter);
		filterRegister.addUrlPatterns("/pan/*");
		return filterRegister;
	}
}
