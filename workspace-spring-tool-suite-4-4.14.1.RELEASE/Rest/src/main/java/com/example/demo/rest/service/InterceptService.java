package com.example.demo.rest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.rest.entity.InterceptEntity;
import com.example.demo.rest.repository.InterceptRepository;

@Service
public class InterceptService {
	
	@Autowired
	private InterceptRepository interceptRepository;
	
	
	public InterceptEntity saveEntity(InterceptEntity interceptEntity) throws IOException
	{ 
		 interceptRepository.saveAndFlush(interceptEntity);
//		 Integer id = interceptEntity.getId();
//		 setIdANDResponse(id);
		 return interceptEntity;
	}
	
//	public void saveResponses(InterceptEntity entity)
//	{
//		String response =  entity.getResponse();
//		setIdANDResponse(response);
//	}
//	
//	public <T> void setIdANDResponse(T val)
//	{
//		T id = val;
//		T response = val;
//		System.out.print("hello id "+id);
//		System.out.print("hello response : " + response);
//		//interceptRepository.setIDAndResponse( response,id);
//	}
}
