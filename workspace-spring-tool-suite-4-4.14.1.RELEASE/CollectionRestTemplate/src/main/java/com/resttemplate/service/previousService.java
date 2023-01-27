package com.resttemplate.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class previousService {

	

//	private String secretKey1="UsK51bCFj6Q=";
//	private SecretKey secretKey;
//	
//	
//	public void generateSecretKey() {
//		try {
//			KeyGenerator kg = KeyGenerator.getInstance("DES");
//			SecretKey key = kg.generateKey();
//			secretKey=key;
//			System.out.println("Secret key:"+Base64.getEncoder().encodeToString(key.getEncoded()));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public String encryptData(String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
//			IllegalBlockSizeException, BadPaddingException {
//		
//		generateSecretKey();
//		
//		Cipher ciper = Cipher.getInstance("DES/ECB/PKCS5Padding");
//		ciper.init(Cipher.ENCRYPT_MODE, secretKey);
//		byte[] encryptedData = ciper.doFinal(data.getBytes());
//		return new String(Base64.getEncoder().encodeToString(encryptedData));
//	}
//
//	public String decryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
//			IllegalBlockSizeException, BadPaddingException {
//		
//		
//		System.out.println("decrypt method called");
//		Cipher ciper = Cipher.getInstance("DES/ECB/PKCS5Padding");
//		ciper.init(Cipher.DECRYPT_MODE, secretKey);
//		System.out.println("after decrypt initialize & secret key is"+secretKey);
//		byte[] decryptedData = ciper.doFinal(Base64.getDecoder().decode(data));
//		return new String(decryptedData);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////
//	DES des;
//	private static final String DES_KEY="asdfghjkl";
//	
//	public String encrypt(String data) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		DES des=new DES(DES_KEY);
//		return des.encryptData(data);
//	}
//	public String decrypt(String data) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		DES des=new DES(DES_KEY);
//		return des.decryptData(data);
//	}
//	
//	
//	private class DES {
//		private SecretKeySpec secretKey;
//		private byte[] key;
//
//		DES(String secret) throws NoSuchAlgorithmException {
//			MessageDigest sha = null;
//			key = secret.getBytes(StandardCharsets.ISO_8859_1);
//			sha = MessageDigest.getInstance("sha-1");
//			key = sha.digest(key);
//			key = Arrays.copyOf(key, 16);
//			secretKey = new SecretKeySpec(key, "DES");
//		}
}
