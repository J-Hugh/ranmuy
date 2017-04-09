package com.neo.admin.system.modular.lbs.domain;

public class MooLbsStation {
    
    /**
      * 
      **/
	private Long id;
    /**
      * 公交线路ID
      **/
	private Long lineId;
	
	private String LineName;
    /**
      * 公交线路序号
      **/
	private Integer stationNum;
    /**
      * 站台名称
      **/
	private String name;
    /**
      * 经度
      **/
	private Double lon;
    /**
      * 纬度
      **/
	private Double lat;
	
	private Double gps_lon;
	private Double gps_lat;
	
	private String xy;
	
	public MooLbsStation() {
		super();
		this.lat=123.2;
		this.lon=123.4;
	}

	
	public MooLbsStation(Long lineId,String LineName, Integer stationNum, String name, Double gps_lon, Double gps_lat) {
		super();
		this.LineName=LineName;
		this.lineId = lineId;
		this.stationNum = stationNum;
		this.name = name;
		this.gps_lon = gps_lon;
		this.gps_lat = gps_lat;
		this.lon = gps_lon;
		this.lat = gps_lat;
	}


	public String getLineName() {
		return LineName;
	}


	public void setLineName(String lineName) {
		LineName = lineName;
	}


	public Double getGps_lon() {
		return gps_lon;
	}


	public void setGps_lon(Double gps_lon) {
		this.gps_lon = gps_lon;
	}


	public Double getGps_lat() {
		return gps_lat;
	}


	public void setGps_lat(Double gps_lat) {
		this.gps_lat = gps_lat;
	}


	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
		try {
			String [] xx = xy.split(",");
			this.lat = Double.parseDouble(xx[1]);
			this.lon = Double.parseDouble(xx[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Integer getStationNum() {
		return stationNum;
	}

	public void setStationNum(Integer stationNum) {
		this.stationNum = stationNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Double getLon() {
		return this.lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

}