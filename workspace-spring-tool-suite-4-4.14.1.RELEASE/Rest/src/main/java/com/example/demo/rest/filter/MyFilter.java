package com.example.demo.rest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.example.demo.rest.entity.InterceptEntity;
import com.example.demo.rest.repository.InterceptRepository;
import com.example.demo.rest.service.EncryptDecryptService;
import com.example.demo.rest.service.InterceptService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class MyFilter implements Filter {

	@Autowired
	private InterceptRepository interceptRepository;

	@Autowired
	private InterceptService interceptService;

	@Autowired
	private EncryptDecryptService encryptDecryptService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		InterceptEntity entity = new InterceptEntity();
		Logger logg = LoggerFactory.getLogger(MyFilter.class);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String myUrl = req.getRequestURI();
		System.out.println("my url : " + myUrl);
		if (myUrl.equals("/encryption") || myUrl.equals("/decryption")) {
			chain.doFilter(req, res);
		} 
		else 
		{
			CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(req);
			String encryptData = cachedBodyHttpServletRequest.getBodyOfData();

			System.out.println("data was : " + encryptData);
			JsonObject jsonObject = new JsonParser().parse(encryptData).getAsJsonObject();
			String value = jsonObject.get("key").getAsString();
			System.out.println("hii it is a password : " + value);
			String bodyDcrypt = encryptDecryptService.decryptMessage(value);
			JsonObject jsonObjectSecond = new JsonParser().parse(bodyDcrypt).getAsJsonObject();
			cachedBodyHttpServletRequest.resetInputStream(jsonObjectSecond.toString().getBytes());

			System.out.println("data: " + bodyDcrypt);
			ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(res);
			chain.doFilter(cachedBodyHttpServletRequest, contentResponse);

			try {
				final byte[] originalData = contentResponse.getContentAsByteArray();
				String data = new String(originalData, contentResponse.getCharacterEncoding());
				System.out.println(" Our data " + data);
				final int originalLength = contentResponse.getContentSize();

				String encrypted = encryptDecryptService.encryptMessage(data);

				System.out.println("Respose Encrypted Data" + encrypted);
				final long newLength = encrypted.length();

				res.setContentLength(encrypted.length());
				res.getOutputStream().write(encrypted.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
