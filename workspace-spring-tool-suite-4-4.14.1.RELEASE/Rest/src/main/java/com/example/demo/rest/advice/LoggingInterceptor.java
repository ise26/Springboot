package com.example.demo.rest.advice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.example.demo.rest.entity.InterceptEntity;
import com.example.demo.rest.service.InterceptService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
	
	@Autowired
	private InterceptService interceptService;

	Logger logg = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		System.out.println("request is : " + request);
		InterceptEntity entity = new InterceptEntity();
		entity = logRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		logResponse(response,entity);
		return response;
	}

	InterceptEntity logRequest(HttpRequest request, byte[] body) throws IOException {
		InterceptEntity entity = new InterceptEntity();
		Random rand = new Random(); 
		int upperbound = 100000;
		int intRandom = rand.nextInt(upperbound);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
		LocalDateTime now = LocalDateTime.now();
		
		entity.setId(intRandom);
		logg.info("" + entity.getId());
		entity.setTime(dtf.format(now));
		logg.info("time is : " + entity.getTime());
		String requestBodyData = new String(body, "UTF-8");
		entity.setRequest(requestBodyData);
		JsonObject jsonObject = new JsonParser().parse(requestBodyData).getAsJsonObject();
		JsonObject userObject =  jsonObject.get("user").getAsJsonObject();
		String instaIds =  userObject.get("instaId").getAsString();
		logg.info("insta is : "+instaIds);
		entity.setContactNumber(instaIds);
		entity = interceptService.saveEntity(entity);
		return entity;
	}

	 void logResponse(ClientHttpResponse response, InterceptEntity entity) throws IOException {
	    InputStreamReader inputStreamReader = new InputStreamReader(response.getBody(),StandardCharsets.UTF_8);
	    String bodyOfResponse = new BufferedReader(inputStreamReader).lines().collect(Collectors.joining("\n"));
	    logg.info("response value is : " + bodyOfResponse);
	    entity.setResponse(bodyOfResponse);
	    interceptService.saveEntity(entity);
	 }
}
