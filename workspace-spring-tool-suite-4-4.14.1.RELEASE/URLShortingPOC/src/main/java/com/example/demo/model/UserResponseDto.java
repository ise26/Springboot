package com.example.demo.model;

import java.time.LocalDateTime;

public class UserResponseDto {

	private String url;
	private String shortLink;
	private LocalDateTime expiryTime;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	@Override
	public String toString() {
		return "UserResponseDto [url=" + url + ", shortLink=" + shortLink + ", expiryTime=" + expiryTime + "]";
	}
	public UserResponseDto(String url, String shortLink, LocalDateTime expiryTime) {
		super();
		this.url = url;
		this.shortLink = shortLink;
		this.expiryTime = expiryTime;
	}
	
	
}
