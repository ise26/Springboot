package com.resttemplate.InterceptorOnLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.tomcat.util.json.JSONParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.resttemplate.Entity.LoggerData;
import com.resttemplate.repository.RestTemplateInterceptorRepo;

@Component
public class InterceptorHandler  implements ClientHttpRequestInterceptor{
	
	@Autowired
	private RestTemplateInterceptorRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	private final Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		LoggerData data= new LoggerData();
		
		data=requestMethod(request, body);
		ClientHttpResponse resp=execution.execute(request, body);
		responseMethod(resp,data);
		return resp;
	}

	
	public LoggerData requestMethod(HttpRequest req,byte[] body) throws IOException {
		
		System.out.println("in the interceptor request method decrypted data:"+req);
		LoggerData data= new LoggerData();
		String request=new String(body,"UTF-8");
		log.info("request body         : {}", new String(body, "UTF-8"));
		String requestbody=new String(body, "UTF-8");
		 System.out.println("Request:"+requestbody);
		
		JsonObject jsonBody= new com.google.gson.JsonParser().parse(requestbody).getAsJsonObject();
		JsonObject getallinfo=jsonBody.get("data").getAsJsonObject();
		
		
		String idNumber=jsonBody.get("id_number").getAsString();
		String mobileNumber=getallinfo.get("mobile_number").getAsString();
		
		data.setRequest(encoder.encode(idNumber));
		data.setMobile_number(mobileNumber);
		String reqTimestampnew=new Date().toString();
		data.setRequesttimestamp(reqTimestampnew);
		data=repo.save(data);
		
		
		System.out.println("Interceptors request method called");
		
		
		return data;
	}
	
	public void responseMethod(ClientHttpResponse resp, LoggerData data) throws IOException {
		
		String response=StreamUtils.copyToString(resp.getBody(), Charset.defaultCharset());
		data.setResponse(response);
		String responseTimestamp=new Date().toString();
		data.setResponsetimestamp(responseTimestamp);
		repo.save(data);
		System.out.println("Interceptors response method called");
	}
	
	
}