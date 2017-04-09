package com.neo.admin.system.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.modular.echarts.domain.Serie;
import com.neo.admin.system.modular.echarts.domain.StackedColumn;
import com.neo.admin.system.modular.lbs.domain.MooLbsDay;
import com.neo.admin.system.modular.lbs.domain.MooLbsVagueLine;
import com.neo.admin.system.modular.lbs.domain.TLbsMinute;
import com.neo.admin.system.modular.lbs.domain.mapEntity.Marker;

/**
 * LBS相关服务类
 * @author neoThe
 *
 */
public interface ILbsFacade {

	
	/**
	 * 分析车辆运行线路
	 * @return
	 * @throws Exception
	 */
	public void analysisLine()  throws Exception;
	
	/**
	 * 分析车辆前一天的上报GPS情况
	 * @throws Exception
	 */
	public void analysis() throws Exception;
	
	/**
	 * 搜索车辆上报情况
	 * @return
	 * @throws Exception
	 */
	BootstrapTable<MooLbsDay> findByPage(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据mac获取某天车辆运行轨迹
	 * @param mac
	 * @param date
	 * @return
	 * @throws Exception
	 */
	List<Marker> getMarkers(String mac,Date date) throws Exception;
	
	/**
	 * 根据mac获取某天车辆程序判断的线路
	 * @param mac
	 * @param date
	 * @return
	 * @throws Exception
	 */
	List<MooLbsVagueLine> getVagueLine(String mac,String date)throws Exception;
	
	/**
	 * 统计前一天上报过GPS数据的车辆数
	 * @throws Exception
	 */
	void busNumPreDay() throws Exception;
	void busNumByMonth(int month) throws Exception ;
	
	/**
	 * 
	 * @param month
	 * @throws Exception
	 */
	public BootstrapTable<TLbsMinute>  queryBusNum(String buyer,int year,int month,String city) throws Exception;
	
	
	//////////////////////////////////////////////
	

	/**
	 * 获取图表数据
	 * @throws Exception
	 */
	//public Map<String, Object> chartdate(String comp,String date) throws Exception;
	
	public List<Serie> chartdate(String comp,String date) throws Exception;
	
	public List<Serie> macChartdate(String buyers, String date,String mac) throws Exception ;
	
	/**
	 * 预加载数据(总量)
	 * @throws Exception
	 */
	public void prestrainChart(String dateStr) throws Exception;
	/**
	 * 预加载数据(总量)
	 * @throws Exception
	 */
	public void prestrainChart() throws Exception;
	/**
	 * 异常报警
	 * @throws Exception
	 */
	public void abnormal() throws Exception;
	
	/**
	 * 每月每日车辆数统计
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public Map<String, Long> busNumDay(int month,int day) throws Exception;
	/**
	 * 每月车辆数统计
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public Map<String, Long> busNumMonth(int month) throws Exception;
	
	/**
	 * 查询某买家，某日所有mac
	 * @param buyers
	 * @param date
	 * @return
	 */
	public Map<String, Integer> allMac(String buyers,String date);
	
	public StackedColumn busNumChart(String startDate,String endDate,String buyer) throws Exception ;
}
