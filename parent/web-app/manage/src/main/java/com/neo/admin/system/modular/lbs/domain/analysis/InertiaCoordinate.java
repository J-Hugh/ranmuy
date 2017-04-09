package com.neo.admin.system.modular.lbs.domain.analysis;

/**
 * 固定坐标
 * @author neoThe
 *
 */
public class InertiaCoordinate {

	/**
	 * 时间戳
	 */
	private long dateStamp;
	/**
	 * 站点编号
	 */
	private Integer stationNum;
	
	public Integer getStationNum() {
		return stationNum;
	}
	public void setStationNum(Integer stationNum) {
		this.stationNum = stationNum;
	}
	public InertiaCoordinate(long dateStamp, Integer stationNum) throws Exception {
		super();
		this.stationNum = stationNum;
		this.dateStamp = dateStamp;
		
	}
	public long getDateStamp() {
		return dateStamp;
	}
	public void setDateStamp(long dateStamp) {
		this.dateStamp = dateStamp;
	}
	
}
