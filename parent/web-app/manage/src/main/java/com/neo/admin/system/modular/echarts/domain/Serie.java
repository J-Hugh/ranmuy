package com.neo.admin.system.modular.echarts.domain;

public class Serie {

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type = "line";
	/**
	 * 是否动画
	 */
	private boolean animation = true;
	/**
	 * 是否显示符号
	 */
	private boolean showSymbol = false;
	/**
	 * 样式
	 */
	private LineStyle lineStyle = new LineStyle();
	/**
	 * 数据集
	 */
	private Integer[] data;
	
	
	public Serie(String name, Integer[] data) {
		super();
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isAnimation() {
		return animation;
	}
	public void setAnimation(boolean animation) {
		this.animation = animation;
	}
	public boolean isShowSymbol() {
		return showSymbol;
	}
	public void setShowSymbol(boolean showSymbol) {
		this.showSymbol = showSymbol;
	}
	public LineStyle getLineStyle() {
		return lineStyle;
	}
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	public Integer[] getData() {
		return data;
	}
	public void setData(Integer[] data) {
		this.data = data;
	}
}
