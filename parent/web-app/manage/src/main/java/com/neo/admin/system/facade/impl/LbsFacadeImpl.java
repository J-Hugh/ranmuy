package com.neo.admin.system.facade.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.DESFileUtil;
import com.neo.admin.common.tools.Httpclient;
import com.neo.admin.common.tools.MathUtil;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.common.value.ConstantMapper;
import com.neo.admin.common.value.WebConfig;
import com.neo.admin.system.facade.ILbsFacade;
import com.neo.admin.system.modular.echarts.domain.Serie;
import com.neo.admin.system.modular.echarts.domain.StackedColumn;
import com.neo.admin.system.modular.echarts.service.EchartsService;
import com.neo.admin.system.modular.file.lbs.service.FileService;
import com.neo.admin.system.modular.file.lbs.service.LbsFileService;
import com.neo.admin.system.modular.lbs.domain.MooLbsDay;
import com.neo.admin.system.modular.lbs.domain.MooLbsVagueLine;
import com.neo.admin.system.modular.lbs.domain.TLbsMinute;
import com.neo.admin.system.modular.lbs.domain.mapEntity.Marker;
import com.neo.admin.system.modular.lbs.service.MooLbsDayService;
import com.neo.admin.system.modular.lbs.service.MooLbsVagueLineService;
import com.neo.admin.system.modular.lbs.service.TLbsMinuteService;

@Service
public class LbsFacadeImpl implements ILbsFacade {
	private static Logger log = LoggerFactory.getLogger(DESFileUtil.class);
	//@Autowired
	//private MongoStationService mongoStationService;
	@Autowired	
	private MooLbsDayService mooLbsDayService;
	@Autowired
	private MooLbsVagueLineService mooLbsVagueLineService;
	@Autowired
	private LbsFileService lbsFileService;
	@Autowired
	private TLbsMinuteService tLbsMinuteService;
	
	@Override
	public void analysisLine() throws Exception {}
	
	@Override
	public void analysis() {}

	@Override
	public BootstrapTable<MooLbsDay> findByPage(Map<String, Object> map) throws Exception {
		return mooLbsDayService.searchByPage(map);
	}

	@Override
	public List<Marker> getMarkers(String mac, java.util.Date date) throws Exception {
		List<Marker> ret = new ArrayList<>();
		
		String filePath = "C:/Users/neoThe/Desktop/LBS/lbs.%s.log";
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHH");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),0,0);
		for(int i = 0;i<24 ;i++){
			c.add(Calendar.HOUR_OF_DAY, 1);
			try {
				BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(String.format(filePath, sdf.format(c.getTime())))),"UTF-8"));  
				try {
					String line = null;
		            // 一次读入一行，直到读入null为文件结束
					long endTime = 0l;
		            while ((line = br.readLine()) != null) {
						String [] xx = line.split("\t");
						if(xx.length == 5){
							if(mac.equals(xx[2])){
								long nowTime = Long.parseLong(xx[1]);
								if(nowTime - endTime > 120){
									ret.add(new Marker(xx[3]));
									endTime = nowTime;
								}
							}
						}
		            }
				} catch (Exception e) {
					e.printStackTrace();
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public List<MooLbsVagueLine> getVagueLine(String mac, String date) throws Exception {
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("apMac", mac);
		searchParams.put("date", date);
		
		return mooLbsVagueLineService.search(searchParams);
	}

	@Override
	public void busNumPreDay() throws Exception {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, -1);
		List<TLbsMinute> minutes = lbsFileService.minuteByDay(ca.getTime());
		if(minutes!= null && !minutes.isEmpty()){
			tLbsMinuteService.deleteByDate(sdf.format(ca.getTime()));
		}
		for(TLbsMinute minute : minutes){
			tLbsMinuteService.save(minute);
		}
	}
	@Override
	public void busNumByMonth(int month) throws Exception {
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MONTH, month - 1);
		
		int dd = (ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		for(int i=1;i<=dd;i++){
			ca.set(Calendar.DAY_OF_MONTH, i);
			
			List<TLbsMinute> minutes = lbsFileService.minuteByDay(ca.getTime());
			if(minutes!= null && !minutes.isEmpty()){
				tLbsMinuteService.deleteByDate(sdf.format(ca.getTime()));
			}
			for(TLbsMinute minute : minutes){
				tLbsMinuteService.save(minute);
			}
		}
	}

	@Override
	public BootstrapTable<TLbsMinute> queryBusNum(String buyer,int year, int month,String city) throws Exception {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.MONTH, month-1);
		int lastDay = (ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		ca.set(Calendar.DAY_OF_MONTH, 0);
		Date start = ca.getTime();
		
		ca.add(Calendar.DAY_OF_MONTH, lastDay);
		Date end = ca.getTime();
		
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("startDate", start);
		searchParams.put("endDate", end);
		searchParams.put("company", buyer);
		searchParams.put("city", city);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(start)+":" + sdf.format(end));
		List<TLbsMinute> minute = tLbsMinuteService.searchByMonth(searchParams);
		BootstrapTable<TLbsMinute> table = new BootstrapTable<>(minute.size(), minute);
		
		return table;
		
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * 缓存的正常规范曲线数据
	 */
	private static Map<String, Map<String, Integer[]>> safeDate = null;
	/**
	 * 缓存的当前曲线数据
	 */
	private static Map<String, Map<String, Integer[]>> nowDate = new HashMap<>();

	/**
	 * 上次发送短信时间戳
	 */
	private static long lastSendtime = 0l;
	@Autowired
	private FileService fileService;
	@Autowired
	private EchartsService echartsService;

	@Override
	public List<Serie> chartdate(String buyers, String date) throws Exception {
		if (nowDate == null || nowDate.isEmpty()) {
			prestrainChart(date);
		}

		Map<String, Integer[]> lbsQuantity = nowDate.get(buyers);
		// 将普通数据转换成Echarts数据格式
		List<Serie> series = echartsService.format(lbsQuantity);
		return series;
	}
	
	@Override
	public List<Serie> macChartdate(String buyers, String date,String mac) throws Exception {
		
		Map<String, Integer[]> lbsQuantity = fileService.lbsQuantity(buyers, date, mac);
		// 将普通数据转换成Echarts数据格式
		List<Serie> series = echartsService.format(lbsQuantity);
		return series;
	}
	

	@Override
	public void prestrainChart(String dateStr) throws Exception {
		// 数据买家
		String[] gpsBuyerss = WebConfig.GPS_BUYERSS.split(",");
		for (String buyers : gpsBuyerss) {
			// 分发城市列表
			String[] citys = (WebConfig.getAttribute1(buyers.trim() + "_CITY")).split(",");
			// 各个城市数据详情统计
			Map<String, Integer[]> lbsQuantity = fileService.lbsQuantity(buyers, dateStr, citys);
			// 缓存数据
			nowDate.put(buyers, lbsQuantity);
		}
	}

	@Override
	public void prestrainChart() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		prestrainChart(dateStr);
	}

	@Override
	public void abnormal() throws Exception {
		
		if (safeDate == null || safeDate.isEmpty()) {
			safeDate = fileService.lbsSafeQuantity();
		}
		// 数据买家
		String[] gpsBuyerss = WebConfig.GPS_BUYERSS.split(",");
		for (String buyers : gpsBuyerss) {
			// 分发城市列表
			String[] citys = (WebConfig.getAttribute1(buyers.trim() + "_CITY")).split(",");
			for (String city : citys) {
				inspect(buyers, city);
			}
		}
	}

	/**
	 * 检测核心函数
	 */
	private void inspect(String buyers, String city) {
		String position = ConstantMapper.getCompany(buyers) + "->" + ConstantMapper.getCity(city);

		Integer[] safe = safeDate.get(buyers).get(city);
		Integer[] now = nowDate.get(buyers).get(city);

		// 获取检测点
		Calendar c = new GregorianCalendar();
		int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
		int minuteOfHour = (c.get(Calendar.MINUTE)) / 2 * 2 - 2;
		int index = (hourOfDay * 60 + minuteOfHour) / 2;

		if (index > 5) {
			// 平滑检测
			Boolean smooth = smooth(position, Arrays.copyOfRange(now, index - 6, index));
			if (smooth) {
				if (safe != null && safe.length != 0) {
					int safeSum = safe[index];
					int nowSum = now[index];
					log.info(String.format("abnormal detailed ：%s safeSum:%s nowSum:%s", position, safeSum, nowSum));
					if(safeSum == 0){
						sendSms("GPS数据分发系统异常：异常信息 " + position + "注：本次检查只验证了平滑度，如有误报，请替换最新的规范文件");
					}else{
						// 对比检测
						Boolean contrast = contrast(safeSum, nowSum);
						if (contrast) {
							sendSms("GPS数据分发系统异常：异常信息 " + position);
						}
					}
				} else {
					sendSms("GPS数据分发系统异常：异常信息 " + position + "注：本次检查只验证了平滑度，如有误报，请替换最新的规范文件");
				}
			}
		}
	}

	/**
	 * 对比检测
	 * 
	 * @param safeSum
	 * @param nowSum
	 * @return true:异常 false:正常
	 */
	private Boolean contrast(int safeSum, int nowSum) {
		// 数据量在1000以上才开始监控
		if (safeSum > 1000) {
			// 降幅达到4/5将报警
			return ((safeSum - nowSum) > safeSum * (4d / 5));
		}
		return false;
	}

	/**
	 * 平滑检测
	 * 
	 * @param sample
	 * @return
	 */
	private Boolean smooth(String position, Integer[] sample) {
		double avg = MathUtil.getAverage(sample);
		double sd = MathUtil.getStandardDiviation(sample);
		if (avg != 0d) {
			double cv = sd / avg;
			log.info(String.format("abnormal detailed ：%s avg:%s sd:%s cv:%s ", position, avg, sd, cv));
			// 变异系数大于0.5报警
			return cv > 0.5;
		} else {
			log.info(String.format("abnormal detailed ：%s avg:%s sd:%s ", position, avg, sd));
		}
		return true;
	}

	private void sendSms(String content) {
		// 发送报警信息后 10分钟内部重复发送
		if (System.currentTimeMillis() - lastSendtime >= 10 * 60 * 1000) {
			log.info("发送报警短信" + content);
			String url = "http://sms.16wifi.com:20001/sms_api/sendMessage.html";
			String[] mobiles = WebConfig.mobiles.split(",");
			for (String mobile : mobiles) {
				if (mobile.length() == 11) {
					CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(Httpclient.config)
							.build();
					Map<String, String> parms = new HashMap<String, String>();
					parms.put("mobile", mobile);
					parms.put("requestApp", "monitor_send");
					parms.put("requestType", "monitor_send_message");
					parms.put("messageContent", content);
					String sendPost = Httpclient.doPost(httpClient, url, null, parms, "");
					log.info(mobile + "短信发送结果：" + sendPost);
				}
			}
			lastSendtime = System.currentTimeMillis();
		}
	}

	@Override
	public Map<String, Long> busNumDay(int month ,int day) throws Exception {
		Map<String, Long> ret = new HashMap<>();
		// 数据买家
		String[] gpsBuyerss = WebConfig.GPS_BUYERSS.split(",");
		for (String buyers : gpsBuyerss) {
			ret.putAll(fileService.busNumDay(month,day, buyers));
		}
		return ret;
	}

	@Override
	public Map<String, Long> busNumMonth(int month) throws Exception {
		Map<String, Long> ret = new HashMap<>();
		// 数据买家
		String[] gpsBuyerss = WebConfig.GPS_BUYERSS.split(",");
		for (String buyers : gpsBuyerss) {
			ret.putAll(fileService.busNumMonth(month, buyers));
		}
		return ret;
	}

	@Override
	public Map<String, Integer> allMac(String buyers, String date) {
		Map<String, Integer> ret = fileService.getMac(buyers, date);

		Iterator<String> keys = ret.keySet().iterator();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			Integer value = ret.get(key);
			if(value > 1000){
				keys.remove();
			}
		}

		return ret;
	}

	@Override
	public StackedColumn busNumChart(String startDate, String endDate, String buyer)throws Exception  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String [] color = {"rgba(0,191,183,1)","rgba(255,144,128,1)","rgba(153,204,255,1)",
				"rgba(255,153,51,1)","rgba(153,153,204,1)","rgba(102,204,204,1)","rgba(102,204,153,1)",
				"rgba(102,102,255,1)","rgba(0,204,204,1)"};
		
		Calendar sca = Calendar.getInstance();
		sca.setTime(sdf.parse(startDate));
		sca.add(Calendar.DAY_OF_MONTH, -1);
		Long startDay = sca.getTimeInMillis(); 
		
		Calendar eca = Calendar.getInstance();
		eca.setTime(sdf.parse(endDate));
		Long endDay = eca.getTimeInMillis();
		
		int difference = (int) ((endDay - startDay)/(1000*60*60*24));
		Map<String, Integer> indexMap = new HashMap<>();
		String [] xdata = new String [difference];
		for(int i = 0;i<difference ;i++){
			indexMap.put(sdf.format(eca.getTime()), difference-1-i);
			xdata[difference-1-i] = sdf.format(eca.getTime());
			eca.add(Calendar.FRIDAY, -1);
		}
		
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("startDate", sdf.parse(startDate));
		searchParams.put("endDate", sdf.parse(endDate));
		searchParams.put("company", buyer);
		
		List<TLbsMinute> minutes = tLbsMinuteService.searchByMonth(searchParams);
		
		Map<String, int []> sign = new HashMap<>();
		int [] sum =new int [difference];
		for(TLbsMinute t : minutes){
			String date = t.getDateStr();
			String city = t.getCity();
			int index = indexMap.get(date);
			if(!sign.containsKey(city)){
				int [] d = new int [difference];
				sign.put(city, d);
			}
			sign.get(city)[index] = t.getBusNum();
			sum[index]= sum[index]+t.getBusNum();
		}
		
		StackedColumn stackedColumn = new StackedColumn();
		Set<String> citySet = new HashSet<>();
		int xx = 0;
		for(Entry<String, int[]> en : sign.entrySet()){
			String city = en.getKey();
			citySet.add(city);
			int[] data = en.getValue();
			stackedColumn.appendBarSerie(ConstantMapper.getCity(city), color[xx++], data);
		}
		stackedColumn.appendLineSerie("总数", sum);
		stackedColumn.setxData(xdata);
		
		String [] legendData = new String[citySet.size()+1];
		int in = 0;
		for(String city : citySet){
			legendData[in++] = ConstantMapper.getCity(city);
		}
		legendData[legendData.length-1] ="总数";
		stackedColumn.setLegendData(legendData);
		return stackedColumn;
		
		//
	}
	
}
