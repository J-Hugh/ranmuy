package com.neo.admin.system.modular.lbs.domain.mapEntity;

/**
 * 標記
 * @author neoThe
 *
 */
public class Marker {

	String []  loc;

	public String[] getLoc() {
		return loc;
	}

	public void setLoc(String[] loc) {
		this.loc = loc;
	}

	public Marker(String loc) {
		super();
		this.loc = loc.split(",");
	}
	
}
