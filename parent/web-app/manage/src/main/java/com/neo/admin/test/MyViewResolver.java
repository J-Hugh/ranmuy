package com.neo.admin.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MyViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		//org.springframework.web.servlet.view.InternalResourceViewResolver
		
		
		HashMap<String, String> dd= new HashMap<>(3, 0.75f);
		System.out.println(dd.size());
		
		return null;
	}
	public static void main(String[] args) {
		HashMap<String, String> dd= new HashMap<>(3, 0.75f);
		
		
		Map<String,String> synmap = Collections.synchronizedMap(dd);
		synmap.put("", "");
		
		
		for(int i = 0 ;i<50 ;i++){
			System.out.println(i +":" +(i & 15));
			System.out.println(i +":" +(i % 16));
		}
	}
}
