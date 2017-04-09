package com.neo.admin.common.value;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ConstantMapper {

	private static Map<String, String> cityfullPinyin = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("shanghai", "上海");
			put("guangzhou", "广州");
			put("kunming", "昆明");
			put("beijing", "北京");
			put("hangzhou", "杭州");
			put("tianjin", "天津");
			put("shaoxing", "绍兴");
			put("changsha", "长沙");
			put("fuzhou", "福州");
			put("shenzhen", "深圳");
			put("foshan", "佛山");
		}
	};
	private static Map<String, String> companyfullPinyin = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("didi", "滴滴");
			put("chelaile", "车来了");
			put("gaode", "高德");
			put("baidu","百度");
		}
	};

	public static String getCompany(String company) {
		String h = companyfullPinyin.get(company.toLowerCase());
		if (StringUtils.isEmpty(h)) {
			return company;
		}
		return h;
	}

	public static String getCity(String city) {
		String h = cityfullPinyin.get(city.toLowerCase());
		if (StringUtils.isEmpty(h)) {
			return city;
		}
		return h;
	}
	public static String[] getCity(String[] citys) {
		String [] ret = new String[citys.length];
		for(int i =0 ;i< citys.length;i++){
			String h = cityfullPinyin.get(citys[i].toLowerCase());
			if(StringUtils.isEmpty(h)){
				ret[i] = citys[i];
			}else{
				ret[i] = h;
			}
		}
		return ret;
	}
	
	public static String [] getLbsxAxis(){
		String [] xx = new String[30*24];
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		Calendar fileD = new GregorianCalendar();
		fileD.setTime(new Date());
		fileD.set(Calendar.HOUR_OF_DAY, 0);
		fileD.set(Calendar.MINUTE, -2);
		fileD.set(Calendar.SECOND, 0);
		for (int i = 0; i < 30 * 24; i++) {
			fileD.add(Calendar.MINUTE, 2);
			String time = sdf1.format(fileD.getTime());
			xx[i]=time;
		}
		return xx;
	}

}
