package com.resttemplate.InterceptorOnLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.resttemplate.Entity.LoggerData;

import net.bytebuddy.asm.Advice.This;

@Component
public class UserDataShowInInterceptorLog implements ClientHttpRequestInterceptor{

	
	private Logger log=LoggerFactory.getLogger(This.class);
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		requestloggs(request,body);
		ClientHttpResponse resp=execution.execute(request, body);
		responseloggs(resp);
		
		return resp;
	}

	public LoggerData requestloggs(HttpRequest req,byte[] body)throws IOException {
		log.info("This is request body :{}",new String(body,"UTF-8"));
		return null;
	}
	public void responseloggs(ClientHttpResponse response)throws IOException {
		log.info("This is response from third partyy api :{}",StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
	}
}
