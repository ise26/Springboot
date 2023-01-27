package com.example.demo.rest.service;

import java.io.ObjectInputStream;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public class EncryptDecryptService {

	public static Map<String, Object> map = new HashMap();
	public static final String ALGORITHM = "RSA";
	public static final String publicK = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAuXUfX9y1eHv0+2uwktgcc5MriyS+jpTnW2tGTJpVaTnEFb54x+0+A+88kbL0GgkXioD/M/eqSGC79Wqpl8smk+W1afI0w9CvZHNF1vARMKgkQhsNu7AKAwVBuwYT+vxJObAUGsRiQ1TAFxYI2qqrJFc7gQRREHAn2bcQLNrDVds2KaXMEVrWrRU+gdEP41my9AFbPw4Y73h3jOVAIaYgK0iKO9XpgDYZJpCy76g/2Ge6eepIOHBCKqcpgxv2wvjeJfE9fxOiUKZTS/oc3aIxNzm7ImbuQMLUmKLTC7pOZT7t3qrL8o3E1jlPmIwXGgQhd1bHYTu7PU+9qmLAHAx5XPZf1sF0XzB/F6j6VIYdfI3NPaAWb4qRWk7Zo/6ZjG5FxDPSwcwrnoBz8K3+GPxWJsy0JSTmzUgivQM1wtVn7kjr4Me9d1LArNBQX8fsp4uvn2Mt1yZi3RHBLbTfNcO82dw87aNegqy3qFiwvJNKS+Dsy4LUZdcSnyBfNYIg2qVlgDIOtrUIGO28mXJ0uDzA3kubwXDwoAVkssXT6GzKfUIW7vUG74sS5gaCNofLFKskoU2pOGolujOG3ObIvcoYWhzpHlC70hYCJwvl7UkW5of+CbXHXItT/xssiKEA7PrMN6ih+UfP4skBVCdECR8dVXwWBBQSoERuwU4K4wTR72kCAwEAAQ==";
    public static final String privateK = "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";
	public void getValue() {
		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyGen.initialize(4096);
			byte[] publicBytesss = Base64.getDecoder().decode(publicK);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytesss);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			final PublicKey pubKey = keyFactory.generatePublic(keySpec);

			
			map.put("publickey", pubKey);
			byte[] privateBytesss = Base64.getDecoder().decode(privateK);
			PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(privateBytesss);
			KeyFactory keyFactorySecond = KeyFactory.getInstance("RSA");
			PrivateKey priK = keyFactory.generatePrivate(keySpecPrivate);

			map.put("privatekey", priK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encryptMessage(String plainText)
	{
		try {
			getValue();
			Cipher cipher = Cipher.getInstance(ALGORITHM); // /ECB/OAEPWITHSHA-512ANDMGF1PADDING
			PublicKey thisPublicKey = (PublicKey) map.get("publickey");
			System.out.println("publick key : " + thisPublicKey);
			System.out.println("success 1");
			cipher.init(Cipher.ENCRYPT_MODE, thisPublicKey);
			System.out.println("success 2");
			byte[] encrypt = cipher.doFinal(plainText.getBytes());
			System.out.println("success 3");
			String encryptData = new String(Base64.getEncoder().encodeToString(encrypt));
			JsonObject object = new JsonObject();
			object.addProperty("key", encryptData);
			System.out.println("success 4");
			String addedString = object.toString();
			System.out.println("added String is : " + addedString);
			return addedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String decryptMessage(String encryptedMessgae) {
		try {
			System.out.println("success 1");
			Cipher cipher = Cipher.getInstance(ALGORITHM); 
			System.out.println("success 2");
			PrivateKey thisPrivateKey = (PrivateKey) map.get("privatekey");
			System.out.println("success 3");
			cipher.init(Cipher.DECRYPT_MODE, thisPrivateKey);
			System.out.println("success 4");
			byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptedMessgae));
			System.out.println("success 5");
			return new String(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "there is something error ";
	}

}
