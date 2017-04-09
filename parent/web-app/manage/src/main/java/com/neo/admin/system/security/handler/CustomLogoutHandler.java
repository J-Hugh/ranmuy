package com.neo.admin.system.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class CustomLogoutHandler implements LogoutHandler {

	private static Logger log = LoggerFactory.getLogger(CustomLogoutHandler.class);
	
	public CustomLogoutHandler() {
		
	}

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		if(authentication != null){
			log.info(String.format("用户： %s 退出系统", authentication.getName()));
		}
	}
}
