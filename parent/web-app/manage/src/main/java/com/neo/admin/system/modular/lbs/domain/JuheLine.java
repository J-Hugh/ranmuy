package com.neo.admin.system.modular.lbs.domain;

import java.util.List;

public class JuheLine {

	private String reason;
	
	private List<MooLbsLine> result;
	
	private Integer error_code;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<MooLbsLine> getResult() {
		return result;
	}

	public void setResult(List<MooLbsLine> result) {
		this.result = result;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}
	
}
