package com.neo.admin.system.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.neo.admin.system.security.RandImage;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
		//验证码判断
		String srandKey = (String) request.getSession().getAttribute(RandImage.RAND_KEY);

		String urequest = request.getParameter("randKey");
		if(StringUtils.isEmpty(urequest)){
			throw new AuthenticationServiceException("登录失败 请输入验证码");
		}
		if(!urequest.toUpperCase().equals(srandKey)){
			throw new AuthenticationServiceException("登录失败 验证码错误");
		}
		
		String username = obtainUsername(request).trim();
		String password = obtainPassword(request);
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// 这里就将token传到后续验证环节了
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainAnswer(HttpServletRequest request) {
		return request.getParameter(answerParameter);
	}

	protected Integer obtainQuestionId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter(questionIdParameter));
	}

	private String questionIdParameter = "questionId";
	private String answerParameter = "answer";

	public String getQuestionIdParameter() {
		return questionIdParameter;
	}

	public void setQuestionIdParameter(String questionIdParameter) {
		this.questionIdParameter = questionIdParameter;
	}

	public String getAnswerParameter() {
		return answerParameter;
	}

	public void setAnswerParameter(String answerParameter) {
		this.answerParameter = answerParameter;
	}

}
