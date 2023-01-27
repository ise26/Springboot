package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Url {
	@Id
	@GeneratedValue
	private int id;
	@Lob
	private String originalUrl;
	private String shortLink;
	private LocalDateTime creationDate;
	private LocalDateTime expiration;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDateTime getExpiration() {
		return expiration;
	}
	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	@Override
	public String toString() {
		return "Url [id=" + id + ", originalUrl=" + originalUrl + ", shortLink=" + shortLink + ", creationDate="
				+ creationDate + ", expiration=" + expiration + "]";
	}
	public Url(int id, String originalUrl, String shortLink, LocalDateTime creationDate, LocalDateTime expiration) {
		super();
		this.id = id;
		this.originalUrl = originalUrl;
		this.shortLink = shortLink;
		this.creationDate = creationDate;
		this.expiration = expiration;
	}
	public Url() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
