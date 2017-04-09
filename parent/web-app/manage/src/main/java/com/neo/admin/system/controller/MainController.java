package com.neo.admin.system.controller;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.system.facade.ISystemFacade;

@Controller
@RequestMapping(value = "/")
public class MainController extends BaseController {
	
	@Autowired
	private ISystemFacade systemFacade;
	
	// 主页
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
		// mooSysLogServiceImpl.insertSysLog();
		
		//System.out.println(new Gson().toJson(systemFacade.menuTree(super.getLogingUserRId())));
		model.addAttribute("menuTree", systemFacade.menuTree(super.getLogingUserRId()));
		
		model.addAttribute("user", super.getLogingUser().getUser());
		
		return "/home";
	}

	// 欢迎页
	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String welcome(HttpServletResponse response, HttpServletRequest request, Model model) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");

		return "/welcome";
	}

	// 主页
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletResponse response, HttpServletRequest request, Model model) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		return "/index";
	}

	// 菜单
	@RequestMapping(value = "menu/{modular}/{menu}", method = RequestMethod.GET)
	public String index(@PathVariable("menu") String menu, @PathVariable("modular") String modular,
			HttpServletResponse response, Model  model) throws Exception  {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		
		if("user".equals(menu)){
			model.addAttribute("roles", systemFacade.roles());
		}
		return String.format("/%s/%s", modular, menu);
	}

	// 错误页跳转
	@RequestMapping(value = "error/{errorcode}")
	public String noAuthority(@PathVariable("errorcode") String errorcode, HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");

		return String.format("/%s", errorcode);
	}

	// 登录跳转
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "注销成功");
		}

		model.setViewName("/login");

		return model;
	}

	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "账号 或密码错误";
		} else if (exception instanceof AuthenticationException) {
			error = exception.getMessage();
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		}else if(exception instanceof AuthenticationServiceException){
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}
}
