package com.neo.admin.system.modular.file.lbs.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.FileUtil;
import com.neo.admin.common.value.WebConfig;

/**
 * 文件相关服务
 * 
 * @author luoyulin
 *
 */
@Service
public class FileService {
	private static Logger logger = LoggerFactory.getLogger(FileService.class);
	/**
	 * 读取LBS记录文件
	 * 
	 * @param buyers
	 * @param dateStr
	 */
	public Map<String, Integer[]> lbsQuantity(String buyers, String dateStr, String[] citys) {
		Map<String, Integer> initialData = new HashMap<>();

		String reportFile0 = String.format(WebConfig.LOG_FILEPATH + "/old_%s_record(%s).log",
				buyers.toLowerCase().trim(), dateStr);
		String reportFile1 = String.format(WebConfig.LOG_FILEPATH + "/new_%s_record(%s).log",
				buyers.toLowerCase().trim(), dateStr);

		readDataFile(reportFile0, initialData);
		readDataFile(reportFile1, initialData);

		Map<String, Integer[]> ret = new HashMap<>();
		for (String city : citys) {
			Integer[] d = curveData(initialData, city);
			ret.put(city, d);
		}
		return ret;
	}
	public Map<String, Integer[]> lbsQuantity(String buyers, String dateStr, String mac) {
		Map<String, Integer> initialData = new HashMap<>();

		String reportFile0 = String.format(WebConfig.LOG_FILEPATH + "/old_%s_record(%s).log",
				buyers.toLowerCase().trim(), dateStr);
		String reportFile1 = String.format(WebConfig.LOG_FILEPATH + "/new_%s_record(%s).log",
				buyers.toLowerCase().trim(), dateStr);

		readDataFile(reportFile0, initialData,mac);
		readDataFile(reportFile1, initialData,mac);

		Map<String, Integer[]> ret = new HashMap<>();
		
		Integer[] d = curveData(initialData);
		ret.put(mac, d);
		return ret;
	}

	
	
	/**
	 * 读取数据文件[根据城市 每两分钟发送的总数据量]
	 * 
	 * @param filePath
	 * @param data
	 *            time_city : quantity
	 */
	private void readDataFile(String filePath, Map<String, Integer> data) {
		List<String> coarseData = FileUtil.read(filePath);
		int i = 0;
		for (String str : coarseData) {
			try {
				// Str = 09:30,shanghai_58696c7aebec_莲金专线,18908
				String[] row = str.split(",");
				if (row.length == 3) {
					String[] mrow = row[1].split("_");
					if (mrow.length > 1) {
						String key = row[0] + "_" + mrow[0];
						if (data.containsKey(key)) {
							int value = data.get(key);
							data.put(key, Integer.parseInt(row[2]) + value);
						} else {
							data.put(key, Integer.parseInt(row[2]));
						}
					} else {
						logger.debug("1error line:" + i + " " + str);
					}
				} else {
					logger.debug("2error line:" + i + " " + str);
				}
				i++;
			} catch (Exception e) {
				logger.debug("3error line:" + i + " " + str);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void readDataFile(String filePath, Map<String, Integer> data,String mac) {
		List<String> coarseData = FileUtil.read(filePath);
		int i = 0;
		for (String str : coarseData) {
			try {
				// Str = 09:30,shanghai_58696c7aebec_莲金专线,18908
				String[] row = str.split(",");
				if (row.length == 3) {
					String[] mrow = row[1].split("_");
					if (mrow.length > 1) {
						if(mac.equals(mrow[1])){
							String key = row[0];
							if (data.containsKey(key)) {
								int value = data.get(key);
								data.put(key, Integer.parseInt(row[2]) + value);
							} else {
								data.put(key, Integer.parseInt(row[2]));
							}
						}
					} else {
						logger.debug("1error line:" + i + " " + str);
					}
				} else {
					logger.debug("2error line:" + i + " " + str);
				}
				i++;
			} catch (Exception e) {
				logger.debug("3error line:" + i + " " + str);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取某城市某天
	 * 
	 * @param rawData
	 *            原始数据
	 * @param city
	 *            城市
	 * @return
	 */
	private Integer[] curveData(Map<String, Integer> initialData, String city) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

		Calendar fileD = new GregorianCalendar();
		fileD.setTime(new Date());
		fileD.set(Calendar.HOUR_OF_DAY, 0);
		fileD.set(Calendar.MINUTE, -2);
		fileD.set(Calendar.SECOND, 0);

		Integer[] quantitys = new Integer[30 * 24];

		for (int i = 0; i < 30 * 24; i++) {
			fileD.add(Calendar.MINUTE, 2);
			String time = sdf1.format(fileD.getTime());
			String key = time + "_" + city.toLowerCase();
			if (initialData.containsKey(key)) {
				quantitys[i] = initialData.get(key);
			} else {
				quantitys[i] = 0;
			}
		}
		return quantitys;
	}
	private Integer[] curveData(Map<String, Integer> initialData) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

		Calendar fileD = new GregorianCalendar();
		fileD.setTime(new Date());
		fileD.set(Calendar.HOUR_OF_DAY, 0);
		fileD.set(Calendar.MINUTE, -2);
		fileD.set(Calendar.SECOND, 0);

		Integer[] quantitys = new Integer[30 * 24];

		for (int i = 0; i < 30 * 24; i++) {
			fileD.add(Calendar.MINUTE, 2);
			String time = sdf1.format(fileD.getTime());
			String key = time;
			if (initialData.containsKey(key)) {
				quantitys[i] = initialData.get(key);
			} else {
				quantitys[i] = 0;
			}
		}
		return quantitys;
	}
	/**
	 * 获取规范的曲线数据
	 * 
	 * @param buyers
	 * @param citys
	 * @return
	 */
	public Map<String, Map<String, Integer[]>> lbsSafeQuantity() {

		Map<String, Map<String, Integer[]>> safeDate = new HashMap<>();

		String[] gpsBuyerss = WebConfig.GPS_BUYERSS.split(",");
		for (String buyers : gpsBuyerss) {
			String[] citys = (WebConfig.getAttribute1(buyers.trim() + "_CITY")).split(",");

			Map<String, Integer> initialData = new HashMap<>();

			String filePath = String.format(WebConfig.LOG_FILEPATH + "/%s_safe.log", buyers.trim().toLowerCase());

			readDataFile(filePath, initialData);

			Map<String, Integer[]> ret = new HashMap<>();
			for (String city : citys) {
				Integer[] d = curveData(initialData, city);
				ret.put(city, d);
			}
			safeDate.put(buyers, ret);
		}
		return safeDate;
	}

	public Map<String, Long> busNumDay(int month,int day, String buyers) {
	
		Map<String, Long> ret = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MONTH, month - 1);
		
		ca.set(Calendar.DAY_OF_MONTH, 0);
		Map<String, Set<String>>  macs = new HashMap<>();
		ca.set(Calendar.DAY_OF_MONTH, day);
		String date = sdf.format(ca.getTime());
		
		String filePath0 = String.format(WebConfig.LOG_FILEPATH + "/old_%s_record(%s).log",buyers.trim().toLowerCase(),date);
		String filePath1 = String.format(WebConfig.LOG_FILEPATH + "/new_%s_record(%s).log",buyers.trim().toLowerCase(),date);
		
		prestrainCarNum(filePath0, macs);
		prestrainCarNum(filePath1, macs);
		
		for(Entry<String, Set<String>> en : macs.entrySet()){
			String city  = en.getKey();
			Set<String> set = macs.get(city);
			if(set != null){
				ret.put(date+"-"+buyers+"-"+city, (long)set.size());
			}else{
				ret.put(date+"-"+buyers+"-"+city, 0l);
			}
		}
		macs.clear();
		return ret;
	}
	
	public Map<String, Long> busNumMonth(int month, String buyers) {
		String[] citys = (WebConfig.getAttribute1(buyers.trim() + "_CITY")).split(",");
		Map<String, Long> ret = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MONTH, month - 1);
		int dd = (ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		ca.set(Calendar.DAY_OF_MONTH, 0);
		
		Map<String, Set<String>>  macs = new HashMap<>();
		for (int i = 0; i < dd; i++) {
			ca.add(Calendar.DAY_OF_MONTH, 1);
			String date = sdf.format(ca.getTime());
			
			String filePath0 = String.format(WebConfig.LOG_FILEPATH + "/old_%s_record(%s).log",buyers.trim().toLowerCase(),date);
			String filePath1 = String.format(WebConfig.LOG_FILEPATH + "/new_%s_record(%s).log",buyers.trim().toLowerCase(),date);
			
			prestrainCarNum(filePath0, macs);
			prestrainCarNum(filePath1, macs);
		}
		for(String city : citys){
			Set<String> set = macs.get(city);
			if(set != null){
				ret.put(buyers+"-"+city, (long)set.size());
			}else{
				ret.put(buyers+"-"+city, 0l);
			}
		}
		return ret;
	}
	
	private void prestrainCarNum(String filePath,Map<String, Set<String>> macs){
		List<String> coarseData = FileUtil.read(filePath);
		int i = 0;
		for(String line : coarseData){
			try {
				//line = 09:30,shanghai_58696c7aebec_莲金专线,18908
				String[] row = line.split(",");
				if (row.length == 3) {
					String[] mrow = row[1].split("_");
					if (mrow.length > 1) {
						String mac = mrow[1];
						if(mac.length() == 12){
							String city = mrow[0].toUpperCase();
							if(macs.containsKey(city)){
								macs.get(city).add(mac);
							}else{
								Set<String> set = new HashSet<>();
								set.add(mac);
								macs.put(city, set);
							}
						}
					} else {
						logger.debug("1 car error line:" + i + " " + line);
					}
				} else {
					logger.debug("2 car error line:" + i + " " + line);
				}
				i++;
			} catch (Exception e) {
				logger.debug("3 car error line:" + i + " " + line);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	public Map<String, Integer> getMac(String buyers,String date){
		Map<String, Integer> ret =  new HashMap<>();
		
		//String filePath0 = String.format(WebConfig.LOG_FILEPATH + "/old_%s_record(%s).log",buyers.trim().toLowerCase(),date);
		String filePath1 = String.format(WebConfig.LOG_FILEPATH + "/new_%s_record(%s).log",buyers.trim().toLowerCase(),date);
		
		
		List<String> coarseData = FileUtil.read(filePath1);
		int i = 0;
		for (String str : coarseData) {
			try {
				// Str = 09:30,shanghai_58696c7aebec_莲金专线,18908
				String[] row = str.split(",");
				if (row.length == 3) {
					String[] mrow = row[1].split("_");
					if (mrow.length > 1) {
						String key = mrow[1]+"_"+mrow[2];
						if (ret.containsKey(key)) {
							int value = ret.get(key);
							ret.put(key, Integer.parseInt(row[2]) + value);
						} else {
							ret.put(key, Integer.parseInt(row[2]));
						}
					} else {
						logger.debug("1error line:" + i + " " + str);
					}
				} else {
					logger.debug("2error line:" + i + " " + str);
				}
				i++;
			} catch (Exception e) {
				logger.debug("3error line:" + i + " " + str);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return ret;
	}
}
