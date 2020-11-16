package br.com.ismarleycarvalho.cripto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
  
  
public class EncriptaDecriptaRSA {

  
  public  KeyPair generateKeyPair(){
	
		try {
 			KeyPairGenerator generator;
			generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
		    KeyPair pair = generator.generateKeyPair();
		    return pair;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	    

	}

  
  public  String encrypt(String plainText, PublicKey publicKey){
	   
	  	try {
		    Cipher encryptCipher = Cipher.getInstance("RSA");
		    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());	    
		    
		    return Base64.getEncoder().encodeToString(cipherText);
	    
	  	}catch (Exception e) {
	  		return null;
		}
  }
  
  public  String decrypt(String cipherText, PrivateKey privateKey){
	   
	  try {
	
	    byte[] bytes = Base64.getDecoder().decode(cipherText);

	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
	    
	    String decode = new String(decriptCipher.doFinal(bytes), "UTF-8");
	    
	    return decode;
	
	    
  	}catch (Exception e) {
		e.printStackTrace();
  		return "ERRO NO DECRIPT";
	}
	
	}
  
  
  
}