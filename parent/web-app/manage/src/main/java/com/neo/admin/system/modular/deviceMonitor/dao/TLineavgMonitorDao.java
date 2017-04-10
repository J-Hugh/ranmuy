package com.neo.admin.system.modular.deviceMonitor.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.deviceMonitor.domain.TLineavgMonitor;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface TLineavgMonitorDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TLineavgMonitor> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TLineavgMonitor> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<TLineavgMonitor> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	TLineavgMonitor findById(String iD);
	
	/**
	 * 保存一条记录
	 * @param tLineavgMonitor
	 */
	void save(TLineavgMonitor tLineavgMonitor);
	
	/**
	 * 通过tLineavgMonitor删除一条记录
	 * @param tLineavgMonitor
	 */
	void delete(String iD);
	
	/**
	 * 修改记录
	 * @param tLineavgMonitor
	 */
	void update(TLineavgMonitor tLineavgMonitor);
}