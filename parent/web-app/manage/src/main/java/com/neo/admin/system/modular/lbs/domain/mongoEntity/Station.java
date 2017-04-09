package com.neo.admin.system.modular.lbs.domain.mongoEntity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.neo.admin.system.modular.lbs.domain.MooLbsStation;
@Document
public class Station {

	/**
	 * 公交线路ID
	 */
	private Long lineId;

	private String lineName;
	/**
	 * 公交线路序号
	 **/
	private Integer stationNum;
	/**
	 * 站台名称
	 **/
	private String name;

	/**
	 * 坐标
	 */
	private Map<String, Object> loc;

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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getLoc() {
		return loc;
	}

	public void setLoc(Map<String, Object> loc) {
		this.loc = loc;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Station(MooLbsStation mooLbsStation) {
		super();
		this.lineId = mooLbsStation.getLineId();
		this.stationNum = mooLbsStation.getStationNum();
		this.name = mooLbsStation.getName();
		this.lineName = mooLbsStation.getLineName();
		Double [] coordinates = new Double[2]; 
		coordinates[0] = mooLbsStation.getLon();
		coordinates[1] = mooLbsStation.getLat();
		Map<String, Object> map = new HashMap<>();
		map.put("type", "Point");
		map.put("coordinates", coordinates);
		this.loc = map;
	}
	
	

}
