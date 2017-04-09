package com.neo.admin.system.controller.lbs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.admin.common.value.ActResult;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.common.value.ConstantMapper;
import com.neo.admin.common.value.WebConfig;
import com.neo.admin.system.controller.BaseController;
import com.neo.admin.system.controller.BaseSearch;
import com.neo.admin.system.facade.impl.LbsFacadeImpl;
import com.neo.admin.system.modular.echarts.domain.Serie;
import com.neo.admin.system.modular.echarts.domain.StackedColumn;
import com.neo.admin.system.modular.lbs.domain.MooLbsDay;
import com.neo.admin.system.modular.lbs.domain.MooLbsVagueLine;
import com.neo.admin.system.modular.lbs.domain.TLbsMinute;
import com.neo.admin.system.modular.lbs.domain.mapEntity.Marker;

/**
 * 
 * @author luoyulin
 *
 */
@Controller
@RequestMapping(value = "lbs")
public class LbsController extends BaseController {
	
	@Autowired
	private LbsFacadeImpl lbsFacadeImpl;

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<?> Refresh(BaseSearch search) {
		ActResult<?> ret = new ActResult<>();
		
		int xx = 12;
		
		try {
			xx = Integer.parseInt(search.getPar().get("month"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			lbsFacadeImpl.busNumByMonth(xx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	@RequestMapping(value = "busnum", method = RequestMethod.GET)
	@ResponseBody
	public BootstrapTable<TLbsMinute> busnum(BaseSearch search) {
		int month = 12;
		int year = 2017;
		try {
			month = Integer.parseInt(search.getPar().get("month"));
			year = Integer.parseInt(search.getPar().get("year"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String buyer = search.getPar().get("buyer");
		String city = search.getPar().get("city");
		try {
			return (lbsFacadeImpl.queryBusNum(buyer,year,month,city));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "apLbss", method = RequestMethod.GET)
	@ResponseBody
	public BootstrapTable<MooLbsDay> dayData(BaseSearch search) throws Exception{
		return lbsFacadeImpl.findByPage(this.objToHash(search));
	}
	
	@RequestMapping(value = "getMarkers", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<List<Marker>> getMarker(BaseSearch search) throws Exception{
		ActResult<List<Marker>> ret = new ActResult<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String mac = (String) search.getPar().get("mac");
		String dateStr = (String) search.getPar().get("date");
		if(StringUtils.isEmpty(dateStr)){
			Calendar   cal   =   Calendar.getInstance();
			cal.add(Calendar.DATE,   -1);
			List<Marker> markers = lbsFacadeImpl.getMarkers(mac, cal.getTime());
			ret.setDataSet(markers);
		}else{
			List<Marker> markers = lbsFacadeImpl.getMarkers(mac, sdf.parse(dateStr));
			ret.setDataSet(markers);
		}
		return ret;
	}
	
	@RequestMapping(value = "getVagueLine", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<List<MooLbsVagueLine>> getVagueLine(BaseSearch search) throws Exception{
		ActResult<List<MooLbsVagueLine>> ret = new ActResult<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String mac = (String) search.getPar().get("mac");
		String dateStr = (String) search.getPar().get("date");
		if(StringUtils.isEmpty(dateStr)){
			Calendar   cal   =   Calendar.getInstance();
			cal.add(Calendar.DATE,   -1);
			List<MooLbsVagueLine> vagueLines = lbsFacadeImpl.getVagueLine(mac, sdf.format(cal.getTime()));
			ret.setDataSet(vagueLines);
		}else{
			List<MooLbsVagueLine> vagueLines = lbsFacadeImpl.getVagueLine(mac, dateStr);
			ret.setDataSet(vagueLines);
		}
		return ret;
	}
	//TODO////////////////////////////////////////////////////////////////
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	// 数据量记录数据
	@RequestMapping(value = "lbsChartdate", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Map<String, Object>> didichartdate(BaseSearch search) throws Exception {
		ActResult<Map<String, Object>> act = new ActResult<>();
		String buyers = search.getPar().get("buyers");
		String date = (StringUtils.isEmpty(search.getKeyWord()) ? sdf.format(new Date()) : search.getKeyWord());
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Serie> series = lbsFacadeImpl.chartdate(buyers, date);
		String[] citys = (WebConfig.getAttribute1(buyers.trim()+"_CITY")).split(",");
		map.put("series", series);
		map.put("citys", ConstantMapper.getCity(citys));
		map.put("xAxis", ConstantMapper.getLbsxAxis());
		act.setSuccess(map);
		return act;
	}
	
	@RequestMapping(value = "temporaryd", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Map<String, Long>> temporaryd(BaseSearch search) throws Exception {
		ActResult<Map<String, Long>> act = new ActResult<>();
		if(search != null){
			if(!StringUtils.isEmpty(search.getKeyWord())){
				try {
					int month = Integer.parseInt(search.getKeyWord());
					int day = Integer.parseInt(search.getPar().get("day"));
					Map<String, Long> map = lbsFacadeImpl.busNumDay(month,day);
					act.setSuccess(map);
				} catch (Exception e) {
					e.printStackTrace();
					act.setFail("参数格式错误");
				}
			}
		}
		return act;
	}
	
	@RequestMapping(value = "temporarym", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Map<String, Long>> bm(BaseSearch search) throws Exception {
		ActResult<Map<String, Long>> act = new ActResult<>();
		if(search != null){
			if(!StringUtils.isEmpty(search.getKeyWord())){
				try {
					int month = Integer.parseInt(search.getKeyWord());
					Map<String, Long> map = lbsFacadeImpl.busNumMonth(month);
					act.setSuccess(map);
				} catch (Exception e) {
					e.printStackTrace();
					act.setFail("参数格式错误");
				}
			}
		}
		return act;
	}

	@RequestMapping(value = "getallmac", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Map<String, Integer>> allMac(BaseSearch search) throws Exception {
		ActResult<Map<String, Integer>> act = new ActResult<>();
		String buyers = search.getPar().get("buyers");
		String date = search.getPar().get("date");
		date = StringUtils.isEmpty(date) ? sdf.format(new Date()) : date;
		
		act.setDataSet(lbsFacadeImpl.allMac(buyers, date));
		
		return act;
	}
	
	
	// 数据量记录数据
	@RequestMapping(value = "macChartdate", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Map<String, Object>> macChartdate(BaseSearch search) throws Exception {
		ActResult<Map<String, Object>> act = new ActResult<>();
		String buyers = search.getPar().get("buyers");
		String date = search.getPar().get("date");
		date = StringUtils.isEmpty(date) ? sdf.format(new Date()) : date;
		String mac =  search.getPar().get("apmac");
		mac = mac.split("_")[0];
		Map<String, Object> map = new HashMap<String, Object>();
		List<Serie> series = lbsFacadeImpl.macChartdate(buyers, date,mac);
		map.put("citys", new String [] {mac});
		map.put("series", series);
		map.put("xAxis", ConstantMapper.getLbsxAxis());
		act.setSuccess(map);
		return act;
	}
	// 车辆数月统计数据
	@RequestMapping(value = "bus_num_chart", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<StackedColumn> busNumChart(BaseSearch search) throws Exception {
		ActResult<StackedColumn> act = new ActResult<>();
		String buyer = search.getPar().get("buyer");
		String start = search.getPar().get("start");
		String end = search.getPar().get("end");
		
		act.setDataSet(lbsFacadeImpl.busNumChart(start, end, buyer));
		return act;
	}
}
