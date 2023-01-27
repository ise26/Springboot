package com.example.demo.model;

public class UrlDto {

	private String url;
	private String expiryDate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "UrlDto [url=" + url + ", expiryDate=" + expiryDate + "]";
	}

	public UrlDto(String url, String expiryDate) {
		super();
		this.url = url;
		this.expiryDate = expiryDate;
	}

}
