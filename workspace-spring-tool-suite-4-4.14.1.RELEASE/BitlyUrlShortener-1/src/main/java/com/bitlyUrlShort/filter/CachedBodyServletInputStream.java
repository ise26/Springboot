package com.bitlyUrlShort.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class CachedBodyServletInputStream extends ServletInputStream{

	 InputStream cacheBodyInputStream;
	
	public CachedBodyServletInputStream(byte[] cacheBody) {
		this.cacheBodyInputStream=new ByteArrayInputStream(cacheBody);
	}

	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return cacheBodyInputStream.read();
	}
	
	
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return true;
	}

	



	@Override
	public void setReadListener(ReadListener listener) {
		// TODO Auto-generated method stub
		
	}

	

}
