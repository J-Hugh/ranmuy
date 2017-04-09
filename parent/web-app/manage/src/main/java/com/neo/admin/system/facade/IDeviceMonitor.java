package com.neo.admin.system.facade;

import java.util.Map;

import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceMonitor;

public interface IDeviceMonitor {

	BootstrapTable<TDeviceMonitor> findByPage(Map<String, Object> map)throws Exception;
}
