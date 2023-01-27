package com.example.demo.rest.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper{

	private byte[] cachedBody;
	CachedBodyServletInputStream inputStream ;
	
	public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
	       InputStream requestInputStream = request.getInputStream();
	       System.out.print("hiii"+requestInputStream.toString());
	        this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
	}
	
	public void resetInputStream(byte[] newdata) {
		cachedBody = newdata;
        inputStream = new CachedBodyServletInputStream(newdata);
        inputStream.cachedBodyInputStream = new ByteArrayInputStream(newdata);
     }
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CachedBodyServletInputStream(cachedBody);
	}
	
	
	
	
	@Override
	public BufferedReader getReader() throws IOException {
		 ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
		 System.out.print("that was : "+cachedBody);
		 return new  BufferedReader(new InputStreamReader(byteArrayInputStream));
	}
	
	
	public String getBodyOfData()
	{
		String data = new String(cachedBody,StandardCharsets.UTF_8);
		return data;
	}
	
}
