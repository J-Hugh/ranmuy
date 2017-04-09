package com.neo.admin.system.modular.monitor.domain;

import org.springframework.util.StringUtils;

/**
 * 汇报数据n
 * 
 * @author luoyulin
 *
 */
public class ReportData {

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 项目名
	 */
	private String projectName;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * 统计时间（hh:mm）
	 */
	private String time;

	/**
	 * 数据类型（LBS,SNIFF,AUTH）
	 */
	private String type;

	/**
	 * 数据量
	 */
	private Long quantity;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return this.time + "-" + this.type + "-" + this.quantity;
	}

	public boolean check(){
		
		boolean notNull =  (!StringUtils.isEmpty(this.city) && 
						!StringUtils.isEmpty(this.ip) &&
						!StringUtils.isEmpty(this.projectName) &&
						!StringUtils.isEmpty(this.time) &&
						!StringUtils.isEmpty(this.type) &&
						quantity != null);
		if(notNull){
			//判断是否含特殊字符
			String specialRegex = ".*[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？].*";
			boolean haveSpe = (this.city + this.projectName + this.type).matches(specialRegex);
			
			String timeRegex = "^\\d{2}:\\d{2}$";
			boolean timeLegal = this.time.matches(timeRegex);
			
			String ipRegex = "^(\\d+\\.){3}\\d+";
			boolean ipLegal = this.ip.matches(ipRegex);
			
			return !haveSpe && timeLegal && ipLegal;
		}else{
			return false;
		}
	}

	public static void main(String[] args) {
		ReportData data = new ReportData();
		data.setCity("shanghai");
		data.setIp("192.168.1.123");
		data.setProjectName("上海网监");
		data.setQuantity(1000l);
		data.setTime("10:23");
		data.setType("LBS");

		//city=shanghai&ip=192.168.1.123&projectName=上海网监&quantity=190&time=12:43&type=LBS

		System.out.println();

	}
}
