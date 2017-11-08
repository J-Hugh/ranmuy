package com.neo.espionage.controller;

import java.util.Map;

public class BaseSearch {

	/**
	 * 开始
	 */
	private int offset;
	/**
	 * 数量
	 */
	private int limit;
	
	/**
	 * 排序
	 */
	private String order;
	
	/**
	 * 搜索key
	 */
	private String keyWord;

	private Map<String,	String> par;
	
	
	
	public Map<String, String> getPar() {
		return par;
	}

	public void setPar(Map<String, String> par) {
		this.par = par;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
