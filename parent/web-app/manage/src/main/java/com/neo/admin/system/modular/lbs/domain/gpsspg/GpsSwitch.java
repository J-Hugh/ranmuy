package com.neo.admin.system.modular.lbs.domain.gpsspg;

import java.util.List;

public class GpsSwitch {

	private String status;
	
	private String msg;
	
	private int count;
	
	private List<NewPoint> result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<NewPoint> getResult() {
		return result;
	}

	public void setResult(List<NewPoint> result) {
		this.result = result;
	}
	
	
}
