package com.bitlyUrlShort.service;

import java.awt.PageAttributes.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.ClientBuilder;

import org.apache.logging.log4j.util.Strings;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.stereotype.Service;

@Service
public class BitlyService {

	
	private String access_token="4006afa5b30d3ef64f8eb63024bbb322fee0f03a";
	private String proxyUri, proxyUsername, proxyPassword;
	
	
	
	public String shortUrl(String longurl) throws UnsupportedEncodingException {
		String encodedUrl=encode(longurl);
		System.out.println(encodedUrl);
		return com.bitlyUrlShort.util.Strings.deleteLastChar(shorten(access_token, encodedUrl));
	}
	
	private String shorten(String access_token, String longUrl) {
		return ClientBuilder
			.newClient(config())
			.target("https://api-ssl.bitly.com")
			.path("v3")
			.path("shorten")
			.queryParam("access_token", access_token)
			.queryParam("longUrl", longUrl)
			.queryParam("format", "txt")
			.request(javax.ws.rs.core.MediaType.TEXT_PLAIN)
			.get(String.class);
	}
	
	private ClientConfig config() {
		ClientConfig clientConfig= new ClientConfig();
		if(proxyUri!=null) {
			clientConfig.connectorProvider(new ApacheConnectorProvider());
			clientConfig.property(ClientProperties.PROXY_URI,proxyUri);
		}
		if(proxyUsername!=null) {
			clientConfig.property(ClientProperties.PROXY_USERNAME, proxyUsername);
			clientConfig.property(ClientProperties.PROXY_PASSWORD, proxyPassword);
			
		}
		return clientConfig;
	}
	
	
	
	//encode the url
	public String encode(String longurl) throws UnsupportedEncodingException {
		return URLEncoder.encode(longurl, "UTF-8");
		
	}
	
	
	//expand short url
	
	public String expand(String shorturl) throws UnsupportedEncodingException {
		String encodedUrl=encode(shorturl);
		System.out.println(encodedUrl);
		return expand(access_token, encodedUrl);
	}
	
	private String expand(String accessToken,String shortUrl) {
		return ClientBuilder
				.newClient(config())
				.target("https://api-ssl.bitly.com")
				.path("v3")
				.path("expand")
				.queryParam("access_token", accessToken)
				.queryParam("longUrl", shortUrl)
				.queryParam("format", "txt")
				.request(javax.ws.rs.core.MediaType.TEXT_PLAIN)
				.get(String.class);
	}
	
	
}
