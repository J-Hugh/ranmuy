package com.neo.admin.system.facade.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.facade.IDeviceMonitor;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceMonitor;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceOnline;
import com.neo.admin.system.modular.deviceMonitor.service.TDeviceMonitorService;
import com.neo.admin.system.modular.deviceMonitor.service.TDeviceOnlineService;

@Service
public class DeviceMonitorFacadeImpl implements IDeviceMonitor {

	@Autowired
	private TDeviceMonitorService deviceMonitorService;
	@Autowired
	private TDeviceOnlineService deviceOnlineService;
	
	@Override
	public BootstrapTable<TDeviceMonitor> findByPage(Map<String, Object> map) throws Exception {
		return deviceMonitorService.searchByPage(map);
	}

	@Override
	public Map<String, Object> recently20Day(String apMac) throws Exception{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 =  new SimpleDateFormat("MM-dd");
		int length = 20;
		
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("apMac", apMac);
		searchParams.put("lastDay", sdf.format(new Date()));
		searchParams.put("length", length);
		List<TDeviceMonitor> data = deviceMonitorService.recently(searchParams);
		
		List<String> xAxisList = new ArrayList<String>();
		Calendar fileD = new GregorianCalendar();
		fileD.setTime(new Date());
		for(int i = 0 ;i<length ;i++){
			fileD.add(Calendar.DAY_OF_YEAR, -1);
			xAxisList.add(sdf1.format(fileD.getTime()));
		}
		Collections.reverse(xAxisList);
		
		int [] a = new int[length];
		int [] b = new int[length];
		int [] c = new int[length];

		for(TDeviceMonitor dm : data){
			String date = sdf1.format(dm.getRecordDate());
			int index = xAxisList.indexOf(date);
			a[index] = dm.getGpsTimes();
			b[index] = dm.getConnTimes();
			c[index] = dm.getHeartbeatTimes();
		}
		String [] xAxisArry= new String [xAxisList.size()];
		xAxisList.toArray(xAxisArry);
		
		Map<String, Object> ret = new HashMap<>();
		ret.put("xAxis", xAxisArry);
		ret.put("a", a);
		ret.put("b", b);
		ret.put("c", c);
		return ret;
	}


	@Override
	public Map<String, Object> recently7DayOnline(String apMac) throws Exception {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 =  new SimpleDateFormat("MM-dd");
		int length = 7;
		
		List<String> xAxisList = new ArrayList<String>();
		Calendar fileD = new GregorianCalendar();
		fileD.setTime(new Date());
		for(int i = 0 ;i<length ;i++){
			fileD.add(Calendar.DAY_OF_YEAR, -1);
			xAxisList.add(sdf1.format(fileD.getTime()));
		}
		
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("apMac", apMac);
		searchParams.put("lastDay", sdf.format(new Date()));
		searchParams.put("length", length);
		List<TDeviceOnline> data = deviceOnlineService.recently(searchParams);
		
		int [][] pane  = new int[24*length][3];
		
		for(int j = 0;j<length;j++){
			for(int i = 0 ;i< 24;i++){
				pane[j*24+i] = new int[]{i,j%length,0};
			}
		}
		for(TDeviceOnline tdo : data){
			String date = sdf1.format(tdo.getRecordDate());
			int index = xAxisList.indexOf(date);
			int [] row = tdo.toArray();
			for(int i = 0 ;i<24;i++){
				int [] obj = new int[3];
				obj[0] = i;
				obj[1] = index;
				obj[2] = row[i];
				pane[index*24+i] = obj;
			}
		}
		System.out.println(new Gson().toJson(pane));
		String [] xAxisArry= new String [xAxisList.size()];
		xAxisList.toArray(xAxisArry);
		Map<String, Object> ret = new HashMap<>();
		ret.put("pane", pane);
		ret.put("xAxis", xAxisArry);
		return ret;
	}
	
	
}
