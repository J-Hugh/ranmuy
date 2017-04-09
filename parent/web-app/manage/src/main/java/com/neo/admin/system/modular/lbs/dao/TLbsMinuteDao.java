package com.neo.admin.system.modular.lbs.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.lbs.domain.TLbsMinute;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface TLbsMinuteDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TLbsMinute> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<TLbsMinute> search(Map<String,Object> searchParams);
	
	/**
	 * 
	 * @param searchParams
	 * @return
	 */
	List<TLbsMinute> searchByMonth(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<TLbsMinute> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	TLbsMinute findById(String iD);
	
	/**
	 * 保存一条记录
	 * @param tLbsMinute
	 */
	void save(TLbsMinute tLbsMinute);
	
	/**
	 * 通过tLbsMinute删除一条记录
	 * @param tLbsMinute
	 */
	void delete(String iD);
	
	void deleteByDate(String date);
	
	/**
	 * 修改记录
	 * @param tLbsMinute
	 */
	void update(TLbsMinute tLbsMinute);
}