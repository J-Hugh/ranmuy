package com.neo.admin.system.modular.deviceMonitor.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceMonitor;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface TDeviceMonitorDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TDeviceMonitor> searchByPage(Map<String,Object> searchParams);
	int total(Map<String,Object> searchParams);
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TDeviceMonitor> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<TDeviceMonitor> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	TDeviceMonitor findById(String iD);
	
	/**
	 * 保存一条记录
	 * @param tDeviceMonitor
	 */
	void save(TDeviceMonitor tDeviceMonitor);
	
	/**
	 * 通过tDeviceMonitor删除一条记录
	 * @param tDeviceMonitor
	 */
	void delete(String iD);
	
	/**
	 * 修改记录
	 * @param tDeviceMonitor
	 */
	void update(TDeviceMonitor tDeviceMonitor);
}