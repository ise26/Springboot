package com.resttemplate.filters;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.resttemplate.service.ResttemplateServiceImpl;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;

@Component
public class RestTemplateFilter extends HttpFilter {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ResttemplateServiceImpl encryptdecryptservice;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		List<String> li= new ArrayList<String>();
		li.add("/encrypt");
		li.add("/decrypt");
		li.add("/demoapicall");
		
		
//		if (((HttpServletRequest) request).getRequestURI().equals("/encrypt") || ((HttpServletRequest) request).getRequestURI().equals("/decrypt") ||((HttpServletRequest) request).getRequestURI().equals("/demoapicall")) {
		
		if(li.contains(request.getRequestURI())) {	
		chain.doFilter(request, response);
		} else {
		
		readDecryptRequestResponse(request, request, chain);
			
		}
	}

	public void readDecryptRequestResponse(HttpServletRequest request,HttpServletRequest request2, FilterChain chain) throws IOException, ServletException {
		ServletContentWrapper newreq = new ServletContentWrapper(request);
		String wrapperRequestToString = newreq.getDataInString();
		System.out.println("this is filter request with Servletwrapper: " + newreq);
		System.out.println("this is filter request converted wrapperRequestToString: " + wrapperRequestToString);
		System.out.println(
				"this is in filter normal request url : " + ((HttpServletRequest) request).getRequestURI());

		try {
			//decryptrequest
			String decryptedRequest = encryptdecryptservice.decryptData(wrapperRequestToString);
			System.out.println("this is filter decrypted from wrapper string " + decryptedRequest);

			JsonObject requestObject = new JsonParser().parse(decryptedRequest).getAsJsonObject();
			
			//convert data in servletrequest request for interceptor
			newreq.sendData(requestObject.toString().getBytes());
			
			//get response and encrypt
			ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) request2);
			
			chain.doFilter(newreq, responseWrapper);
			
			try {
				byte[] data=responseWrapper.getContentAsByteArray();
				String dataInString=new String(data,responseWrapper.getCharacterEncoding());
				String encryptedData=encryptdecryptservice.encryptData(dataInString);
				( (ServletResponse) request2).setContentLength(encryptedData.length());
				((ServletResponse) request2).getWriter().write(encryptedData);
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
