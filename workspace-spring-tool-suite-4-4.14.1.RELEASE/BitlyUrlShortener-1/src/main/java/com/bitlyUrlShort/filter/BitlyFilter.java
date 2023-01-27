package com.bitlyUrlShort.filter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bitlyUrlShort.service.BitlyService;

@Component
public class BitlyFilter extends HttpFilter {

	@Autowired
	private BitlyService service;

	public static String decryptedToken;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		List<String> li = new ArrayList<String>();
		li.add("/encryptToken");

		if (li.contains(request.getRequestURI())) {
			chain.doFilter(request, response);
		} else {
			String encryptedToken = request.getHeader("Authorization");

			String token = encryptedToken.substring(7);

			try {
				String decryptedToken = service.decodeToken(token);
				System.out.println("Decrypted token in filter is: " + decryptedToken);
				request.setAttribute("decryptedToken", decryptedToken);

			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chain.doFilter(request, response);
		}

	}

}
