package com.neo.admin.system.modular.echarts.domain;

import java.util.HashMap;
import java.util.Map;

public class LineStyle {

	private Map<String, Object> normal;

	public Map<String, Object> getNormal() {
		return normal;
	}

	public void setNormal(Map<String, Object> normal) {
		this.normal = normal;
	}

	public LineStyle() {
		super();
		Map<String, Object> normal = new HashMap<>();
		normal.put("width", 1);
		this.normal = normal;
	}
	
}
