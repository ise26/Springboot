package com.bitlyUrlShort.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.http.HttpRequest;
import org.springframework.util.StreamUtils;

import com.bitlyUrlShort.filter.CachedBodyServletInputStream;

public class ServletContentWrapper extends HttpServletRequestWrapper{

	CachedBodyServletInputStream input;
	private byte[] requestbody;
	
	public ServletContentWrapper(HttpServletRequest request) throws IOException {
		super(request);
		InputStream requestInputStream=request.getInputStream();
		this.requestbody=StreamUtils.copyToByteArray(requestInputStream);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		
		return new CachedBodyServletInputStream(this.requestbody);
	}
	@Override
	public BufferedReader getReader() throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.requestbody);
	    return new BufferedReader(new InputStreamReader(byteArrayInputStream));
	}
	
	public String getDataInString() {
		return new String(this.requestbody,StandardCharsets.UTF_8);
	}
	
	public void sendData(byte[] data) {
		requestbody=data;
		input=new CachedBodyServletInputStream(data);
		input.cacheBodyInputStream=new ByteArrayInputStream(data);
	}
}
