package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Url;
import com.example.demo.model.UrlDto;
import com.example.demo.repository.ShortUrlRepository;
import com.google.common.hash.Hashing;

@Service
public class UrlServiceImpl implements UrlService {
	
	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@Override
	public Url generateShortLink(UrlDto urlDto) {

		if (StringUtils.isNotEmpty(urlDto.getUrl())) {

			String encodedUrl = getEncodetUrl(urlDto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setExpiration(getExpiryTime(urlDto.getExpiryDate(), urlToPersist.getCreationDate()));
			urlToPersist.setShortLink(encodedUrl);
			urlToPersist.setOriginalUrl(urlDto.getUrl());
			Url urlToRest = persistShortLink(urlToPersist);
			
			return urlToRest;
		}
		return null;
	}

	private LocalDateTime getExpiryTime(String expiryTime, LocalDateTime creationDate) {

		if (StringUtils.isBlank(expiryTime)) {
			return creationDate.plusMinutes(20);
		}
		LocalDateTime expirationTime = LocalDateTime.parse(expiryTime);
		return expirationTime;

	}

	private String getEncodetUrl(String url) {

	
		String encodedUrl = null;
		LocalDateTime dateTime = LocalDateTime.now();

		encodedUrl = Hashing.murmur3_32().hashString(url.concat(dateTime.toString()), StandardCharsets.UTF_8)
				.toString();
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		
		Url urlToRet = shortUrlRepository.save(url);
		
		return urlToRet;
	}

	@Override
	public Url encodedShortLink(String url) {
		Url urlToRet = shortUrlRepository.findByShortUrlLink(url);
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {
		// TODO Auto-generated method stub

	}

}
