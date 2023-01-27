package com.bitlyUrlShort.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class BitlyService {

	public static SecretKeySpec secretKey;
	public static final String generatedSecretKey="37mY7AT71c0=";
	
	public String generatekey() throws NoSuchAlgorithmException {
		
		KeyGenerator keyGen= KeyGenerator.getInstance("DES");
		SecretKey key =keyGen.generateKey();
		String secretKey=Base64.getEncoder().encodeToString(key.getEncoded());
		
		return secretKey;
	}
	
	public void getkey() {
		byte[] decodedKey=Base64.getDecoder().decode(generatedSecretKey);
		secretKey= new SecretKeySpec(decodedKey, "DES");
	}
	
	public String encodeToken(String token) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		getkey();
		Cipher cipher= Cipher.getInstance("DES/ECB/PKCS5PADDING");
		cipher.init(cipher.ENCRYPT_MODE,secretKey );
		byte[] encodedData=cipher.doFinal(token.getBytes());
		return new String(Base64.getEncoder().encodeToString(encodedData));
	}
	
	public String decodeToken(String encryptedToken) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		getkey();
		Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5PADDING");
		cipher.init(cipher.DECRYPT_MODE, secretKey);
		byte[] decodedData=cipher.doFinal(Base64.getDecoder().decode(encryptedToken));
		return new String(decodedData);
	}
}
