package com.neo.admin.common.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.neo.admin.common.value.WebConfig;

/**
 * 短信相关服务
 * @author luoyulin
 *
 */
public class SmsUtil {

	private static Logger log = LoggerFactory.getLogger(DESFileUtil.class);
	/**
	 * 发送间隔
	 */
	private static Map<String, AtomicLong> lastSend = new HashMap<>();
	
	/**
	 * 发送短信
	 * @param content
	 * @param marker 
	 * @param interval
	 */
	public static void sendSms(String content,String marker,long interval) {
		String[] mobiles = WebConfig.waMobiles.split(",");
		
		for(String mobile : mobiles){
			if(StringUtils.isEmpty(mobile) || mobile.length() != 11){
				continue;
			}
			if(StringUtils.isEmpty(marker)){
				send(content,mobile);
			}
			if(lastSend.containsKey(marker)){
				AtomicLong last = lastSend.get(marker);
				if (System.currentTimeMillis() - last.get() >= interval) {
					send(content,mobile);
					last.set(System.currentTimeMillis());
				}
			}else{
				send(content,mobile);
				lastSend.put(marker, new AtomicLong(System.currentTimeMillis()));
			}
		}
	}
	
	private static void send(String content,String mobile){
		log.info("发送短信" + content);
		String url = "http://sms.16wifi.com:20001/sms_api/sendMessage.html";
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(Httpclient.config).build();
		//String[] mobiles = WebConfig.mobiles.split(",");

		Map<String, String> parms = new HashMap<String, String>();
		parms.put("mobile", mobile);
		parms.put("requestApp", "monitor_send");
		parms.put("requestType", "monitor_send_message");
		parms.put("messageContent", content);
		String sendPost = Httpclient.doPost(httpClient, url, null, parms, "");
		log.info("短信发送结果：" + sendPost);

	}
	
}
