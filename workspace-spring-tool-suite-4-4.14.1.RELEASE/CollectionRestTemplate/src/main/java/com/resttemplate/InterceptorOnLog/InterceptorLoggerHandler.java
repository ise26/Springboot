package com.resttemplate.InterceptorOnLog;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.resttemplate.Entity.LoggerData;
import com.resttemplate.controller.RestController;
import com.resttemplate.repository.RestTemplateInterceptorRepo;

//@Component
public class InterceptorLoggerHandler implements ClientHttpRequestInterceptor {
	@Autowired
	private RestTemplateInterceptorRepo repo;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

//		Object requestSavedData = logReq(request, body);
//		System.out.println("requestDataAfterSave " + requestSavedData);
//		ClientHttpResponse resp = execution.execute(request, body);
//		logResponse(resp, requestSavedData);

		
		logReq(request, body);//2nd
		ClientHttpResponse resp=execution.execute(request, body);
		logResponse(resp);//3rd
		return resp;

	}

	public LoggerData logReq(HttpRequest req, byte[] body) throws IOException {

		log.info("URI         : {}", req.getURI());
		log.info("request body         : {}", new String(body, "UTF-8"));
		log.info("request method : {}", req.getMethod());
		log.info("request headers : {}", req.getHeaders());
		log.info("request class : {}", req.getClass());
		return null;

//		LoggerData data = new LoggerData();//4th
//		String requestdata = new String(body, "UTF-8");
//		String timestamp = new Date().toString();
//		data.setRequest(requestdata);
//		data.setRequesttimestamp(timestamp);
//		data =repo.save(data);//5th
//		return data;//6th
	}

	public void logResponse(ClientHttpResponse resp) throws IOException {

		log.info("--------------------------After Request-----------------------------------");
		log.info("response statustext : {}", resp.getStatusText());
		log.info("response stattus code : {}", resp.getStatusCode());
		log.info("response headers : {}", resp.getHeaders());
		log.info("response body : {}", StreamUtils.copyToString(resp.getBody(), Charset.defaultCharset()));

//		LoggerData data = (LoggerData) requestDataId;
//		System.out.println("requestDataId:" + data.getId());
//		String restimestamp = new Date().toString();
//		System.out.println("restimestamp " + restimestamp);
//		System.out.println(rrr);
//		
//		String r = StreamUtils.copyToString(resp.getBody(), Charset.defaultCharset());
//		log.info("result is this  : "+r);
//		data.setResponse(r);
//		data.setResponsetimestamp(new Date().toString());
//		repo.save(data);
//		 
		
		

	}
}
