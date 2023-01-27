package com.panVerification.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.panVerification.PanDetails;
import com.panVerification.entity.Result;

@RestController
public class TempController {

	@Value("${url}")
	private String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/getData")
	public Object getData(@RequestBody com.panVerification.entity.PanDetails panNumber) {
		
		HttpHeaders header = new HttpHeaders();
		
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		header.set("developer_key","becbbce45f79c6f5109f848acd540567");
		header.set("secret-key","MC6dKW278tBef+AuqL/5rW2K3WgOegF0ZHLW/FriZQw=");
		header.set("secret-key-timestamp","1516705204593");
		
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
		map.add("pan_number",panNumber.getPan_number());
		map.add("purpose",1);
		map.add("initiator_id","9971771929");
		map.add("purpose_desc","onboarding");
		
		HttpEntity<Object> entity = new HttpEntity(map,header);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		
		return response.getBody();
	}
	
	@GetMapping("/createCollection")
	public String createCollection() throws IOException {
		FileWriter fw =  new FileWriter("D:/NSDLcollection.json");
		
		String data=restTemplate.getForObject("http://localhost:8080/v3/api-docs", String.class);
		System.out.println("data:"+data);
		fw.write(data);
		fw.close();
		return data;
	}
	
}
