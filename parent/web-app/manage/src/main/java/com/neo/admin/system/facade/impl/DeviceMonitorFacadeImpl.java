package com.neo.admin.system.facade.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.facade.IDeviceMonitor;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceMonitor;
import com.neo.admin.system.modular.deviceMonitor.service.TDeviceMonitorService;

@Service
public class DeviceMonitorFacadeImpl implements IDeviceMonitor {

	@Autowired
	private TDeviceMonitorService deviceMonitorService;

	@Override
	public BootstrapTable<TDeviceMonitor> findByPage(Map<String, Object> map) throws Exception {

		return deviceMonitorService.searchByPage(map);

	}

}
