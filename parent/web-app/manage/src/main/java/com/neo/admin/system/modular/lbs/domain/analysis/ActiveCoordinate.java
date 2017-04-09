package com.neo.admin.system.modular.lbs.domain.analysis;

/**
 * 活性的四维坐标点
 * @author neoThe
 *
 */
public class ActiveCoordinate {

	/**
	 * 时间
	 */
	private long date;
	
	/**
	 * 物理经纬度
	 */
	private double [] loc;

	
	public ActiveCoordinate(long date, double[] loc) {
		super();
		this.date = date;
		this.loc = loc;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public double[] getLoc() {
		return loc;
	}

	public void setLoc(double[] loc) {
		this.loc = loc;
	}
	
}
