package com.example.demo.rest.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class CachedBodyServletInputStream extends ServletInputStream {

    InputStream cachedBodyInputStream;
	
	public CachedBodyServletInputStream(byte[] cachedBody)
	{
		this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
	}
	
	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener listener) {
		
	}

	@Override
	public int read() throws IOException {
		return cachedBodyInputStream.read();
	}

}
