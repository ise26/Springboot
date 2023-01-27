package com.urlshortner.service;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.urlshortner.UrlRepo;
import com.urlshortner.dto.UrlDto;

@Service
public class UrlService {

	@Autowired
	private UrlRepo repo;

	public String createUrl(String url) {
		LocalDateTime time= LocalDateTime.now();
		
		
		
		String shorturl = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
		UrlDto dtoObject = new UrlDto();
		dtoObject.setId(shorturl);
		dtoObject.setUrl(url);
		System.out.println(shorturl);
		List<String> li= repo.getshortids();
		System.out.println(li);
		if(li.isEmpty()) {
			repo.save(dtoObject);
		}else if(!li.contains(shorturl)){
			System.out.println("do");
			repo.save(dtoObject);
		}else {
		}
		
		
		
		return shorturl;
	}

	public List<UrlDto> getUrl(String id) {

		return repo.geturl(id);

	}
	
	public String getOgUrl(String url) {
	
		String ogurlstring=repo.getog(url);
		System.out.println(ogurlstring+" here is url");
		return ogurlstring;
	}
}
