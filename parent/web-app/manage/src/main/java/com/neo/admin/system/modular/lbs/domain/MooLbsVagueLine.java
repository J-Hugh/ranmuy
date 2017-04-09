package com.neo.admin.system.modular.lbs.domain;

public class MooLbsVagueLine {
    
    /**
      * 
      **/
	private Long id;
    /**
      * 设备MAC
      **/
	private String apMac;
    /**
      * 预判线路名称
      **/
	private String lineName;
    /**
      * 是否已确定0-确定 1-不确定
      **/
	private String isDetermine;
    /**
      * 匹配数量
      **/
	private String spotNum;
    /**
      * 分析日期
      **/
	private String date;


	

	public MooLbsVagueLine(String apMac, String lineName, String spotNum, String date) {
		super();
		this.apMac = apMac;
		this.lineName = lineName;
		this.spotNum = spotNum;
		this.date = date;
		this.isDetermine = "1";
	}

	public MooLbsVagueLine() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getApMac() {
		return this.apMac;
	}

	public void setApMac(String apMac) {
		this.apMac = apMac;
	}
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getIsDetermine() {
		return this.isDetermine;
	}

	public void setIsDetermine(String isDetermine) {
		this.isDetermine = isDetermine;
	}
	public String getSpotNum() {
		return this.spotNum;
	}

	public void setSpotNum(String spotNum) {
		this.spotNum = spotNum;
	}
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}