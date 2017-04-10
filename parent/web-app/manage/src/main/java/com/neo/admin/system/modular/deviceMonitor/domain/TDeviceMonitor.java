package com.neo.admin.system.modular.deviceMonitor.domain;

public class TDeviceMonitor {
    
    /**
      * 
      **/
	private Long id;
    /**
      * 
      **/
	private String apMac;
    /**
      * 开机时长（秒）
      **/
	private Integer onlineTimeLength;
    /**
      * 关机次数
      **/
	private Integer shutDownTimes;
    /**
      * 合理值 值越小越精确
      **/
	private Integer linkage;
    /**
      * 移动距离
      **/
	private Integer moveDistance;
    /**
      * GPS上报次数
      **/
	private Integer gpsTimes;
    /**
      * 心跳次数
      **/
	private Integer heartbeatTimes;
    /**
      * 联网次数
      **/
	private Integer connTimes;
    /**
      * 城市
      **/
	private String city;
    /**
      * 统计时间
      **/
	private java.sql.Date recordDate;
    /**
      * 盒子制造商
      **/
	private String manufacturer;
    /**
      * 公交线路
      **/
	private String lineName;
    /**
      * 认证次数
      **/
	private Integer authTimes;

	private Double error;

	public TDeviceMonitor() {
		super();
	}

	public Double getError() {
		return error;
	}

	public void setError(Double error) {
		this.error = error;
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
	public Integer getOnlineTimeLength() {
		return this.onlineTimeLength;
	}

	public void setOnlineTimeLength(Integer onlineTimeLength) {
		this.onlineTimeLength = onlineTimeLength;
	}
	public Integer getShutDownTimes() {
		return this.shutDownTimes;
	}

	public void setShutDownTimes(Integer shutDownTimes) {
		this.shutDownTimes = shutDownTimes;
	}
	public Integer getLinkage() {
		return this.linkage;
	}

	public void setLinkage(Integer linkage) {
		this.linkage = linkage;
	}
	public Integer getMoveDistance() {
		return this.moveDistance;
	}

	public void setMoveDistance(Integer moveDistance) {
		this.moveDistance = moveDistance;
	}
	public Integer getGpsTimes() {
		return this.gpsTimes;
	}

	public void setGpsTimes(Integer gpsTimes) {
		this.gpsTimes = gpsTimes;
	}
	public Integer getHeartbeatTimes() {
		return this.heartbeatTimes;
	}

	public void setHeartbeatTimes(Integer heartbeatTimes) {
		this.heartbeatTimes = heartbeatTimes;
	}
	public Integer getConnTimes() {
		return this.connTimes;
	}

	public void setConnTimes(Integer connTimes) {
		this.connTimes = connTimes;
	}
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public java.sql.Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(java.sql.Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public Integer getAuthTimes() {
		return this.authTimes;
	}

	public void setAuthTimes(Integer authTimes) {
		this.authTimes = authTimes;
	}
}