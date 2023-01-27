package com.example.demo.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.configuration.BuilderConfiguration;
@RestController
public class Controller1 {
	@Autowired
	private RestTemplate rt;
	
	@Value("${posts}")
	String posts;
	
	@Value("${users}")
	private String user;
	
	
	
	@GetMapping(value = "/getuser/{id}", produces ="application/json" )
	public String getEntity(@PathVariable int id){
		
		String response = rt.getForObject(user+"/"+id, String.class);
		
		return response;
		
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<List> getList(){
		
		//String url="https://jsonplaceholder.typicode.com/users";
		ResponseEntity<List> response = rt.getForEntity(user, List.class);
		
		return response;
		
	}
	
	@GetMapping("/exchange/{id}")
	public String exchangeDemo(@PathVariable int id) {
		//String url = "https://jsonplaceholder.typicode.com/posts/"+id;
		
		HttpHeaders header = new HttpHeaders();
		HttpEntity entity = new HttpEntity(header);
		ResponseEntity<String> re = rt.exchange(posts+"/+id", HttpMethod.GET,entity, String.class);
		
		return re.getBody();
	
	}
	
	
	
	@PostMapping(value = "postentity")
	public Posts postEntity(@RequestBody Posts p) {
		
		//String url="https://jsonplaceholder.typicode.com/posts";
		ResponseEntity<Posts> response = rt.postForEntity(posts,p,Posts.class);
		return response.getBody(); 
		
	}
	
	@PostMapping(value = "postobject", produces = "application/json")
	public Posts postObject(@RequestBody Posts p) {
		
		//String url="https://jsonplaceholder.typicode.com/posts";
		Posts response = rt.postForObject(posts,p,Posts.class);
	
		return response;
		
	}
	
	@GetMapping("/createcollection")
	public String createcollection() throws IOException {
		 FileWriter file = new FileWriter("D:/createdcollection.json");
         String data =  rt.getForObject("http://localhost:8080/v3/api-docs",String.class);
         System.out.println("data : " + data);
         file.write(data);
         file.close();
		return data;
	}
}
