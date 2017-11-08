package com.neo.utils.tools;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 短信相关服务
 * @author luoyulin
 *
 */
public class SmsUtil {

	/**
	 * 发送间隔
	 */
	private static Map<String, AtomicLong> lastSend = new HashMap<>();
	
	private static void send(String content,String mobile){
		//log.info("发送短信" + content);
		String url = "http://sms.16wifi.com:20001/sms_api/sendMessage.html";
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(Httpclient.config).build();
		//String[] mobiles = WebConfig.mobiles.split(",");

		Map<String, String> parms = new HashMap<String, String>();
		parms.put("mobile", mobile);
		parms.put("requestApp", "monitor_send");
		parms.put("requestType", "monitor_send_message");
		parms.put("messageContent", content);
		String sendPost = Httpclient.doPost(httpClient, url, null, parms, "");
		//log.info("短信发送结果：" + sendPost);
		System.out.println(sendPost+"===");
	}

}
