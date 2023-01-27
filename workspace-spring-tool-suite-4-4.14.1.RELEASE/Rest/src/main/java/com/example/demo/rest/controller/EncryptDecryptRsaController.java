package com.example.demo.rest.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.service.EncryptDecryptService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class EncryptDecryptRsaController {

	@Autowired
	private EncryptDecryptService encryptDecryptService;
	
	@PostMapping("/encryption")
	public String encryptMessage(@RequestBody String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		String encrypt =  encryptDecryptService.encryptMessage(message);
		return encrypt;
	}
	
	@PostMapping("/decryption")
	public String decryptMessage(@RequestBody String message)
	{
		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		String value =  jsonObject.get("key").getAsString();
		System.out.println("encrypted value was : " + value);
		String decrypt =  encryptDecryptService.decryptMessage(value);
		return decrypt;
	}
}
