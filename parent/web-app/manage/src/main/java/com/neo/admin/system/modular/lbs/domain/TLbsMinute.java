package com.neo.admin.system.modular.lbs.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TLbsMinute {
    
    /**
      * 
      **/
	private Long id;
    /**
      * 公司
      **/
	private String company;
    /**
      * 城市
      **/
	private String city;
    /**
      * 车辆数
      **/
	private Integer busNum;
    /**
      * 日期
      **/
	private Date date;

	private String dateStr;

	
	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public TLbsMinute() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public Integer getBusNum() {
		return this.busNum;
	}

	public void setBusNum(Integer busNum) {
		this.busNum = busNum;
	}
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.dateStr =sdf.format(date);
	}

	public TLbsMinute(String company, String city, Integer busNum, Date date) {
		super();
		this.company = company;
		this.city = city;
		this.busNum = busNum;
		this.date = date;
	}

}