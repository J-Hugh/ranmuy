package com.neo.utils.tools;

import org.apache.commons.net.util.Base64;


import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.Key;


public class DESFileUtil {


	/** 加密工具 */
	private Cipher encryptCipher = null;

	/** 解密工具 */
	private Cipher decryptCipher = null;

	private static String KEY = "pbwj$#@!";
	
	private static String IV = "salt#&@!";
	
	private static DESFileUtil instance = null;

	private DESFileUtil(Cipher encryptCipher, Cipher decryptCipher) {
		super();
		this.encryptCipher = encryptCipher;
		this.decryptCipher = decryptCipher;
	}

	private static synchronized void syncInit() throws Exception {
		if (instance == null) {
			IV = IV == null ? "salt#&@!" : IV;
			KEY = KEY == null ? "pbwj$#@!" : KEY;
			// 向量
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			// 密钥
			Key key = getKey(KEY.getBytes());

			Cipher encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key, iv);

			Cipher decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, key, iv);
			instance = new DESFileUtil(encryptCipher, decryptCipher);
		}
	}

	private static Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	public static DESFileUtil getInstance() throws Exception {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	/**
	 * 对文件加密
	 * 
	 * @param sourceFileName
	 *            需要加密的文件
	 * @param diminationFileName
	 *            加密后的文件
	 * @throws Exception
	 */
	public void encrypt(String sourceFileName, String diminationFileName) throws Exception {
		// logger.info("加密文件：" + sourceFileName +"到\n" + diminationFileName);
		InputStream is = new FileInputStream(sourceFileName);
		OutputStream out = new FileOutputStream(diminationFileName);
		byte [] b = input2Byte(is);
		
		int xxx = (b.length+8)%64;
		
		byte [] a =new byte[b.length+8+xxx];
		
		for(int i=0;i<b.length;i++){
			a[i] = b[i];
		}
		byte [] len = DESFileUtil.intToByte(b.length, 4);
		
		a[b.length] = len[0];
		a[b.length+1] = len[1];
		a[b.length+2] = len[2];
		a[b.length+3] = len[3];
		
		a[b.length+4] = 65;
		a[b.length+5] = 66;
		a[b.length+6] = 67;
		a[b.length+7] = 68;
		
		CipherInputStream cis = new CipherInputStream(new ByteArrayInputStream(a), encryptCipher);
		
		byte[] buffer = new byte[1024];
		int r;
		while ((r = cis.read(buffer)) > 0) {
			out.write(buffer, 0, r);
		}
		cis.close();
		is.close();
		out.close();
		
		InputStream is2 = new FileInputStream(diminationFileName);
		Base64 base64 = new Base64();
		byte[] dd = base64.encode(input2Byte(is2));
		
		byte2File(dd, diminationFileName);
		
		//logger.info(String.format("加密【%s】成功", sourceFileName));
	}

	public static byte[]  input2Byte(InputStream in) throws IOException{
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        byte[] bs = new byte[1024];   
        int len = -1;  
        while ((len = in.read(bs)) != -1) {  
            bos.write(bs, 0, len);  
        }  
        byte b[] = bos.toByteArray();
        bos.close();  
        
        return b;
	}
	
	public static byte[] intToByte(int i, int len) {
		byte[] abyte = null;
		if (len == 1) {
			abyte = new byte[len];
			abyte[0] = (byte) (0xff & i);
		} else {
			abyte = new byte[len];
			abyte[0] = (byte) ((i >>> 24) & 0xff);
			abyte[1] = (byte) ((i >>> 16) & 0xff);
			abyte[2] = (byte) ((i >>> 8) & 0xff);
			abyte[3] = (byte) (i & 0xff);
		}
		return abyte;
	}

	public static int bytesToInt(byte[] bytes) {
		int addr = 0;
		if (bytes.length == 1) {
			addr = bytes[0] & 0xFF;
		} else {
			addr = bytes[0] & 0xFF;
			addr = (addr << 8) | (bytes[1] & 0xff);
			addr = (addr << 8) | (bytes[2] & 0xff);
			addr = (addr << 8) | (bytes[3] & 0xff);
		}
		return addr;
	}
	
	/**
	 * 解密文件
	 * 
	 * @param sourceFileName
	 *            需要解密的文件
	 * @param diminationFileName
	 *            解密后的文件存放地址
	 * @throws Exception
	 */
	public void decrypt(String sourceFileName, String diminationFileName) throws Exception {
		
		InputStream is2 = new FileInputStream(sourceFileName);
		byte [] b =input2Byte(is2);
		Base64 base64 = new Base64();
		
		byte2File(base64.decode(b), sourceFileName+"2");
		InputStream is = new FileInputStream(sourceFileName+"2");
		OutputStream out = new FileOutputStream(diminationFileName);
		CipherOutputStream cos = new CipherOutputStream(out, decryptCipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = is.read(buffer)) >= 0) {
			cos.write(buffer, 0, r);
		}
		cos.close();
		out.close();
		is.close();
	}

	 public void byte2File(byte[] buf, String filePath)  
	    {  
	        BufferedOutputStream bos = null;  
	        FileOutputStream fos = null;  
	        File file = null;  
	        try  
	        {  
	            File dir = new File(filePath);  
	            if (!dir.exists() && dir.isDirectory())  
	            {  
	                dir.mkdirs();  
	            }  
	            file = new File(filePath);  
	            fos = new FileOutputStream(file);  
	            bos = new BufferedOutputStream(fos);  
	            bos.write(buf);  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	        finally  
	        {  
	            if (bos != null)  
	            {  
	                try  
	                {  
	                    bos.close();  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (fos != null)  
	            {  
	                try  
	                {  
	                    fos.close();  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
	
	public Cipher getEncryptCipher() {
		return encryptCipher;
	}

	public void setEncryptCipher(Cipher encryptCipher) {
		this.encryptCipher = encryptCipher;
	}

	public Cipher getDecryptCipher() {
		return decryptCipher;
	}

	public void setDecryptCipher(Cipher decryptCipher) {
		this.decryptCipher = decryptCipher;
	}

}
