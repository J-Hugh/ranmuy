package com.neo.utils.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static void write(String filePath,List<String> msgs)  throws Exception{
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				if (file.createNewFile()) {
					file = new File(filePath);
				}
			}
			FileWriter writer = new FileWriter(file, true);
			BufferedWriter lines = new BufferedWriter(writer);
			try {
				for(String msg: msgs)
				lines.write(msg+"\n");
			} finally {
				lines.flush();
				lines.close();
				writer.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void write(String filePath,String msg) throws Exception{
		
		File file = new File(filePath);

		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			if (file.createNewFile()) {
				file = new File(filePath);
			}
		}
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter lines = new BufferedWriter(writer);
		try {
			lines.write(msg+"\n");
		} finally {
			lines.flush();
			lines.close();
			writer.close();
		}
	}
	/**
	 * 读取记录文件
	 * 
	 * @return
	 */
	public static List<String> read(String filePath) {
		List<String> list = new ArrayList<>();
		try {
			File file = new File(filePath);
			if(file.exists()){
				list = read(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> read(File file) {
		List<String> list = new ArrayList<>();
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
			
			try {
				String tempString = null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = br.readLine()) != null) {
	                // 显示行号
	            	list.add(tempString);
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
