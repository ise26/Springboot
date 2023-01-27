package com.resttemplate.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.resttemplate.Entity.LoggerData;
import com.resttemplate.Entity.ModelClass;
import com.resttemplate.repository.RestTemplateInterceptorRepo;

import net.bytebuddy.asm.Advice.This;

@Service
public class ResttemplateServiceImpl {

	private static final String secretKey1="UsK51bCFj6Q=";
	private SecretKeySpec secretKey;
	
	
	public void generateSecretKey() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			SecretKey key = kg.generateKey();
			//secretKey=key;
			String newSecretkey=Base64.getEncoder().encodeToString(key.getEncoded());
			System.out.println("Secret key:"+Base64.getEncoder().encodeToString(key.getEncoded()));
					} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getKey() {
		byte[] decodeKey=Base64.getDecoder().decode(secretKey1);
		secretKey=new SecretKeySpec(decodeKey,"DES");

	}

		public String encryptData(String data) throws NoSuchPaddingException, InvalidKeyException,
				NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {

			generateSecretKey();
			getKey();
			Cipher ciper = Cipher.getInstance("DES/ECB/PKCS5Padding");
			ciper.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedData = ciper.doFinal(data.getBytes());
			return new String(Base64.getEncoder().encodeToString(encryptedData));
		}

		public String decryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException,
				InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			getKey();
			System.out.println("decrypt method called");
			Cipher ciper = Cipher.getInstance("DES/ECB/PKCS5Padding");
			ciper.init(Cipher.DECRYPT_MODE, secretKey);
			System.out.println("after decrypt initialize & secret key is" + secretKey);
			byte[] decryptedData = ciper.doFinal(Base64.getDecoder().decode(data));
			return new String(decryptedData);
		}
	//}

}
