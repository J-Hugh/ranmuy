package com.neo.admin.common.value;

import java.util.ResourceBundle;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	private static ResourceBundle res = ResourceBundle.getBundle("properties.webconfig");
	
	//百度编辑器图片上传保存地址
	public static String UEDITOR_UPLOAD_IMAGE=res.getString("ueditor.upload.image");
	
	//网站名称
	public static String SSI_WEBSITE_NAME = res.getString("ssi.website.name");
	//网站域名
	public static String SSI_WEBSITE_DOMAIN = res.getString("ssi.website.domain");
	//网站地址
	public static String SSI_WEBSITE_URL = res.getString("ssi.website.url");
	
	public static String LOG_FILEPATH = res.getString("LOG_FILEPATH");
	
	//数据购买方公司名称
	public static String GPS_BUYERSS = res.getString("GPS_BUYERSS");
	/**
	 * 系统异常时需要发送短信的手机号
	 */
	public static String mobiles = res.getString("MOBILES");
	/**
	 * 网监系统发送手机号
	 */
	public static String waMobiles = res.getString("WAMOBILES");
	/**
	 * 根据属性名获取值
	 * @param key
	 * @return
	 */
	public static String getAttribute1(String key){
		return res.getString(key);
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
