package com.neo.admin.system.modular.echarts.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 堆叠柱状图
 * @author neoThe
 *
 */
public class StackedColumn {

	
	private List<Map<String, Object>> series = new ArrayList<>();
	
	private String [] legendData;
	
	private String [] xData;

	public List<Map<String, Object>> getSeries() {
		return series;
	}

	public void setSeries(List<Map<String, Object>> series) {
		this.series = series;
	}

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public String[] getxData() {
		return xData;
	}

	public void setxData(String[] xData) {
		this.xData = xData;
	}
	
	public void appendBarSerie (String name ,String color,int [] data){
		
		Map<String, Object> serie = new HashMap<>();
		
		serie.put("name", name);
		serie.put("type", "bar");
		serie.put("stack", "总量");
		serie.put("barMaxWidth", "35");
		serie.put("barGap", "10%");
		
		Map<String, Object> itemStyle = new HashMap<>();
		Map<String, Object> normal = new HashMap<>();
		normal.put("color", color);
		Map<String, Object> label = new HashMap<>();
		label.put("show", true);
		label.put("position", "insideTop");
		Map<String, Object> textStyle = new HashMap<>();
		textStyle.put("color", "#fff");
		
		label.put("textStyle", textStyle);
		normal.put("label", label);
		itemStyle.put("normal",normal);
		serie.put("itemStyle", itemStyle);
	
		serie.put("data", data);
		
		this.series.add(serie);
	}
public void appendLineSerie (String name ,int [] data){
		
		Map<String, Object> serie = new HashMap<>();
		
		serie.put("name", name);
		serie.put("type", "line");
		serie.put("stack", "总量");
		serie.put("symbolSize", "10");
		serie.put("symbol", "circle");
	
		Map<String, Object> itemStyle = new HashMap<>();
		Map<String, Object> normal = new HashMap<>();
		normal.put("color", "rgba(252,230,48,1)");
		normal.put("barBorderRadius", "0");
		
		Map<String, Object> label = new HashMap<>();
		label.put("show", true);
		label.put("position", "top");
		normal.put("label", label);
		itemStyle.put("normal",normal);
		serie.put("itemStyle", itemStyle);
	
		serie.put("data", data);
		
		this.series.add(serie);
	}
}
