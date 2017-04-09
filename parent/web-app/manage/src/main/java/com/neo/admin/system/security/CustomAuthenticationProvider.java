package com.neo.admin.system.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// 转换为自定义的token
		/*CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		String poem = LoginQuestion.getQuestions().get(token.getQuestionId());
		// 校验下一句的答案是否正确
		if (!poem.split("/")[1].equals(token.getAnswer())) {
			throw new BadAnswerException("the answer is wrong!");
		}*/
		
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		 throw new UsernameNotFoundException("24333333333333333333333333333");
	}
	@Override
	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return super.authenticate(arg0);
	}
}
