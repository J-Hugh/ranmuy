package  com.neo.admin.system.modular.monitor.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neo.admin.common.tools.FileUtil;
import com.neo.admin.common.tools.MathUtil;
import com.neo.admin.common.tools.SmsUtil;

/**
 * 
 * @author luoyulin
 *
 */
public class MonitorService implements Runnable {

	private static Logger log = LoggerFactory.getLogger(MonitorService.class);

	/**
	 * 检查数据长度
	 */
	private static final Integer INSPECT_LENGTH = 6;

	private File file;

	public MonitorService(File file) {
		super();
		this.file = file;
	}

	@Override
	public void run() {
		
		String fileName = file.getName();
		String position = fileName.replaceAll("\\.rep", "");

		// 数据类型
		Set<String> types = new HashSet<>();
		// 原始统计数据
		List<String> oriReport = FileUtil.read(file);
		// 处理后的统计数据
		Map<String, Long> reportFormat = format(oriReport, types);
		// 获取监测点
		List<String> point = inspectPoint(INSPECT_LENGTH);

		for (String type : types) {
			double[] dataSet = new double[INSPECT_LENGTH];
			for (int i = 0; i < INSPECT_LENGTH; i++) {
				String key = point.get(i) + type;
				if (reportFormat.containsKey(key)) {
					dataSet[i] = reportFormat.get(key);
				} else {
					dataSet[i] = 0d;
				}
			}
			analyse(dataSet, position,type);
		}
	}
	/**
	 * 分析数据是否异常
	 * 
	 * @param dataSet
	 * @param key
	 *            数据标识
	 */
	private void analyse(double[] dataSet, String position,String type) {
		double avg = MathUtil.getAverage(dataSet);
		double sd = MathUtil.getStandardDiviation(dataSet);
		if(avg == 0d){
			log.info(position+"["+type+"]"+"检测结果 ： avg" +avg+" : sd"+sd);
			SmsUtil.sendSms("网监异常 异常信息："+position+"["+type+"]", "网安", 10*60*1000);
		}else{
			//变异系数
			double cv = sd / avg;
			log.info(position+"["+type+"]"+"检测结果 ： avg" +avg+" : sd"+sd+" : cv"+cv);
			if(cv > 1.5){
				SmsUtil.sendSms("网监异常 异常信息："+position+"["+type+"]", "网安", 10*60*1000);
			}
		}
	}

	/**
	 * 处理原始数据
	 * 
	 * @param ori
	 * @param type
	 * @return
	 */
	private Map<String, Long> format(List<String> ori, Set<String> type) {
		Map<String, Long> reportFormat = new HashMap<>();
		for (String s : ori) {
			String[] ar = s.split("-");
			if (ar.length == 3) {
				try {
					String key = ar[0] + ar[1];
					Long value = Long.parseLong(ar[2]);
					reportFormat.put(key, value);
					type.add(ar[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ori.clear();
		return reportFormat;
	}

	/**
	 * 获取检查点
	 * 
	 * @param inspectLength
	 * @return
	 */
	private List<String> inspectPoint(int inspectLength) {
		List<String> list = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");

		Calendar c = new GregorianCalendar();
		int minuteOfDay = (c.get(Calendar.MINUTE)) / 2 * 2;
		c.set(Calendar.MINUTE, minuteOfDay);

		for (int i = 0; i < INSPECT_LENGTH; i++) {
			list.add(df.format(c.getTime()));
			c.add(Calendar.MINUTE, -2);
		}
		return list;
	}
	/*private String getPosition(String fileName) {

		String regex = "\\[(.*?)\\]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(fileName);

		while (m.find()) {
			System.out.println(m.group(1));
		}
		return "";
	}*/

}
