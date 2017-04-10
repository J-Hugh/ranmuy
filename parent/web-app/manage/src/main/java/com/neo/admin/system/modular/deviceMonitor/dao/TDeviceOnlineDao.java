package com.neo.admin.system.modular.deviceMonitor.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.deviceMonitor.domain.TDeviceOnline;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface TDeviceOnlineDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TDeviceOnline> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TDeviceOnline> search(Map<String,Object> searchParams);
	List<TDeviceOnline> recently(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<TDeviceOnline> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	TDeviceOnline findById(String iD);
	
}