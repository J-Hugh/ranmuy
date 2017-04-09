package com.neo.admin.system.modular.file.lbs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.FileUtil;
import com.neo.admin.common.value.WebConfig;
import com.neo.admin.system.modular.lbs.domain.TLbsMinute;

@Service
public class LbsFileService {

	public List<String> readLbsLog(){
		String filePath = "C:/Users/neoThe/Desktop/LBS/lbs.%s.log";
		
		List<String> ret = new ArrayList<>();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHH");
		
		Calendar c = new GregorianCalendar();
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)-1,0,0);
		
		for(int i = 0;i<24 ;i++){
			c.add(Calendar.HOUR_OF_DAY, 1);
			ret.addAll(FileUtil.read(String.format(filePath, sdf.format(c.getTime()))));
		}
		return ret;
	}
	
	public List<TLbsMinute> minuteByDay(Date date){
		List<TLbsMinute> ret = new ArrayList<>();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		
		
		String[] gpsBuyerss = WebConfig.GPS_BUYERSS.split(",");
		for (String buyers : gpsBuyerss) {
			String filePath = String.format(WebConfig.LOG_FILEPATH + "/new_%s_record(%s).log",buyers.toLowerCase(),sdf.format(date));
			try {
				BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),"UTF-8"));  
				Map<String, HashSet<String>> r = new HashMap<>();
				try {
					String line = null;
		            // 一次读入一行，直到读入null为文件结束
		            while ((line = br.readLine()) != null) {
		            	String [] a = line.split(",");
		            	if(a.length == 3) {
		            		String [] b = a[1].split("_");
		            		if(b.length == 3){
		            			String city = b[0];
		            			String mac = b[1];
		            			if(!r.containsKey(city)){
		            				r.put(city, new HashSet<String>());
		            			}
		            			r.get(city).add(mac);
		            		}
		            	}
		            }
				} catch (Exception e) {
					e.printStackTrace();
				}
				br.close();
				for(Entry<String, HashSet<String>>  en : r.entrySet()){
					String city = en.getKey();
					ret.add(new TLbsMinute(buyers, city, en.getValue().size(), date));
				}
				r.clear();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
