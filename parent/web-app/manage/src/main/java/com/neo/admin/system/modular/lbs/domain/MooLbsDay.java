package com.neo.admin.system.modular.lbs.domain;

import java.sql.Timestamp;

public class MooLbsDay {

	/**
	  * 
	  **/
	private Long id;
	/**
	 * mac
	 **/
	private String mac;
	/**
	 * 默认线路
	 **/
	private String lineName;
	/**
	 * 开始时间
	 **/
	private String statrTime;
	/**
	 * 结束时间
	 **/
	private String endTime;
	/**
	 * 理论活跃时长
	 */
	private Integer theoryActive;
	/**
	 * 活跃时长
	 **/
	private Integer active;
	
	/**
	 * 有效时长率
	 */
	private double effectiveRate;
	/**
	 * 最大延时
	 **/
	private Integer maxDelayed;
	/**
	 * 上传次数
	 **/
	private Integer upTimes;
	/**
	 * 系统判断得出的线路
	 **/
	private String judgeLineName;
	/**
	 * 日期
	 **/
	private String date;

	private java.sql.Timestamp analysisDate;

	public double getEffectiveRate() {
		return effectiveRate;
	}

	public void setEffectiveRate(double effectiveRate) {
		this.effectiveRate = effectiveRate;
	}

	public String getDate() {
		return date;
	}

	public Integer getTheoryActive() {
		return theoryActive;
	}

	public void setTheoryActive(Integer theoryActive) {
		this.theoryActive = theoryActive;
	}

	public java.sql.Timestamp getAnalysisDate() {
		return analysisDate;
	}

	public void setAnalysisDate(java.sql.Timestamp analysisDate) {
		this.analysisDate = analysisDate;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MooLbsDay() {
		super();
	}

	public MooLbsDay(String mac, String date, String statrTime, String endTime, Integer active, Integer maxDelayed,
			Integer upTimes) {
		super();
		this.mac = mac;
		this.statrTime = statrTime;
		this.endTime = endTime;
		this.active = active;
		this.maxDelayed = maxDelayed;
		this.upTimes = upTimes;
		this.date = date;
		this.analysisDate = new Timestamp(System.currentTimeMillis());
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getStatrTime() {
		return this.statrTime;
	}

	public void setStatrTime(String statrTime) {
		this.statrTime = statrTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getMaxDelayed() {
		return this.maxDelayed;
	}

	public void setMaxDelayed(Integer maxDelayed) {
		this.maxDelayed = maxDelayed;
	}

	public Integer getUpTimes() {
		return upTimes;
	}

	public void setUpTimes(Integer upTimes) {
		this.upTimes = upTimes;
	}

	public String getJudgeLineName() {
		return this.judgeLineName;
	}

	public void setJudgeLineName(String judgeLineName) {
		this.judgeLineName = judgeLineName;
	}
}