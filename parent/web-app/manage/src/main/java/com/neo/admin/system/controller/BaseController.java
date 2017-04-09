package com.neo.admin.system.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.neo.admin.common.value.ActResult;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;
import com.neo.admin.system.security.LoginUserDetails;

public class BaseController {

	// 当前登录用户信息
	public LoginUserDetails getLogingUser() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if ("anonymousUser".equals(obj)) {
			return null;
		} else {
			return (LoginUserDetails) obj;
		}
	}
	// 当前登录用户的ROLE
	public String[] getLogingUserRId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj != null) {
			LoginUserDetails user = (LoginUserDetails) obj;

			List<MooSysRole> roles = user.getUser().getRoles();
			String[] rIds = new String[roles.size()];
			for (int i = 0; i < roles.size(); i++) {
				rIds[i] = roles.get(i).getRoleId();
			}
			return rIds;
		}
		return null;
	}

	// 当前登录用户信息
	public String getLogingUserId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if ("anonymousUser".equals(obj)) {
			return null;
		} else {
			LoginUserDetails user = (LoginUserDetails) obj;
			return user.getUser().getuId();
		}
	}

	// 处理返回结果
	public void handleRet(ActResult<?> ret, String msg) {
		if ("SUCCESS".equals(msg)) {
			ret.setSuccess("处理成功");
		} else {
			ret.setFail(msg);
		}
	}

	// 将javabean转为map类型，然后返回一个map类型的值
	public HashMap<String, Object> objToHash(Object obj) throws IllegalArgumentException, IllegalAccessException {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Class<?> clazz = obj.getClass();
		List<Class<?>> clazzs = new ArrayList<Class<?>>();
		do {
			clazzs.add(clazz);
			clazz = clazz.getSuperclass();
		} while (!clazz.equals(Object.class));

		for (Class<?> iClazz : clazzs) {
			Field[] fields = iClazz.getDeclaredFields();
			for (Field field : fields) {
				Object objVal = null;
				field.setAccessible(true);
				objVal = field.get(obj);
				hashMap.put(field.getName(), objVal);
			}
		}

		/*
		 * if(getLogingUserId()!=null){ hashMap.put("LoginUserId",
		 * getLogingUserId()); }
		 */

		return hashMap;
	}
}
