package com.neo.admin.system.controller.deviceMonitor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.controller.BaseController;
import com.neo.admin.system.controller.BaseSearch;
import com.neo.admin.system.facade.impl.DeviceMonitorFacadeImpl;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceMonitor;

/**
 * 
 * @author luoyulin
 *
 */
@Controller
@RequestMapping(value = "device_monitor")
public class BasicController extends BaseController {
	
	@Autowired
	private DeviceMonitorFacadeImpl deviceMonitorFacade;

	
	@RequestMapping(value = "basic", method = RequestMethod.GET)
	@ResponseBody
	public BootstrapTable<TDeviceMonitor> busnum(BaseSearch search) throws Exception {
		return deviceMonitorFacade.findByPage(this.objToHash(search));
	}
	
	@RequestMapping(value = "recently20Day", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> recently20Day(BaseSearch search) throws Exception {
		
		return deviceMonitorFacade.recently20Day(search.getPar().get("apmac"));
	}
	
	@RequestMapping(value = "recently7DayOnline", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> recently7DayOnline(BaseSearch search) throws Exception {
		
		return deviceMonitorFacade.recently7DayOnline(search.getPar().get("apmac"));
	}
	
}
