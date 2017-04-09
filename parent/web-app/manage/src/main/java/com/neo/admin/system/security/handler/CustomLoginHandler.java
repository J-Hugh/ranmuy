package com.neo.admin.system.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.neo.admin.system.security.LoginUserDetails;

public class CustomLoginHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	private static Logger log = LoggerFactory.getLogger(CustomLoginHandler.class);
	
	//@Autowired
	//private MooLoginRecordService mooLoginRecordServiceImpl;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);
		 
		LoginUserDetails user= (LoginUserDetails) authentication.getPrincipal();
		//WebAuthenticationDetails web=(WebAuthenticationDetails) authentication.getDetails();
		
		log.info(String.format("用户: %s 登录系统", user.getUsername()));
		//添加登录记录
		//mooLoginRecordServiceImpl.save(new MooLoginRecord(user.getUserId(), web.getRemoteAddress(), "PC端"));
		
	}
}
