package com.neo.admin.system.controller.deviceMonitor;

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
	
	
}
