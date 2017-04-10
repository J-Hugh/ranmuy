package com.neo.admin.system.modular.deviceMonitor.domain;

public class TLineavgMonitor {
    
    /**
      * 
      **/
	private Long id;
    /**
      * 线路ID
      **/
	private String lineId;
    /**
      * 线路名
      **/
	private String lineName;
    /**
      * 平均在线时长
      **/
	private Long avgOnline;
    /**
      * 平均关机次数
      **/
	private Long avgShutdown;
    /**
      * 平均位移
      **/
	private Long avgDistance;
    /**
      * 平均上报GPS次数
      **/
	private Long avgLbs;
    /**
      * 平均心跳上报次数
      **/
	private Long avgHeartbeat;
    /**
      * 平均联网次数
      **/
	private Long avgConn;
    /**
      * 统计时间
      **/
	private java.sql.Date recordDate;


	public TLineavgMonitor() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getLineId() {
		return this.lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public Long getAvgOnline() {
		return this.avgOnline;
	}

	public void setAvgOnline(Long avgOnline) {
		this.avgOnline = avgOnline;
	}
	public Long getAvgShutdown() {
		return this.avgShutdown;
	}

	public void setAvgShutdown(Long avgShutdown) {
		this.avgShutdown = avgShutdown;
	}
	public Long getAvgDistance() {
		return this.avgDistance;
	}

	public void setAvgDistance(Long avgDistance) {
		this.avgDistance = avgDistance;
	}
	public Long getAvgLbs() {
		return this.avgLbs;
	}

	public void setAvgLbs(Long avgLbs) {
		this.avgLbs = avgLbs;
	}
	public Long getAvgHeartbeat() {
		return this.avgHeartbeat;
	}

	public void setAvgHeartbeat(Long avgHeartbeat) {
		this.avgHeartbeat = avgHeartbeat;
	}
	public Long getAvgConn() {
		return this.avgConn;
	}

	public void setAvgConn(Long avgConn) {
		this.avgConn = avgConn;
	}
	public java.sql.Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(java.sql.Date recordDate) {
		this.recordDate = recordDate;
	}
}