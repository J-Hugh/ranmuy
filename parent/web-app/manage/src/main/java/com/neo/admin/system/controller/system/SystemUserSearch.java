package com.neo.admin.system.controller.system;

import com.neo.admin.system.controller.BaseSearch;

public class SystemUserSearch extends BaseSearch {

	/**
	 * 用户名
	 */
	private String uName;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
}
