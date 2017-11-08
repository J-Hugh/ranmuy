package com.neo.utils.tools;


import org.apache.commons.codec.binary.*;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DESUtils {
	
	private Cipher cipher;
	
	/**
	 * 构造函数
	 * @param encryptKey
	 * @param ivStr
	 */
	public DESUtils(String encryptKey,String ivStr) {
		try {
			IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
			DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加密
	 * @param encryptString
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public String encrypt(String encryptString) throws Exception{
		return new String(Base64.encodeBase64(cipher.doFinal(encryptString.getBytes())));
	}
	
	public static String decrypt(String decryptString, String decryptKey, String ivStr) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return new String(cipher.doFinal(Base64.decodeBase64(decryptString)));
	}
	public static String encrypt(String encryptString, String encryptKey, String ivStr) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
		DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return new String(Base64.encodeBase64(cipher.doFinal(encryptString.getBytes())));
	}

	public static void main(String[] args) {
		try {
			String encryptString = "0.0.1\0145\014.3.1\01fa\0131000021020000\01119.364123\0123.0023\0120151207021323\010\0166";
			String encryptKey = "o$@8q1tc";
			String ivStr = "salt#&@!";
			System.out.println(encrypt(encryptString, encryptKey, ivStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
