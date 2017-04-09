package com.neo.admin.system.modular.lbs.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.lbs.domain.MooLbsStation;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface MooLbsStationDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooLbsStation> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooLbsStation> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<MooLbsStation> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	MooLbsStation findById(String iD);
	
	/**
	 * 保存一条记录
	 * @param mooLbsStation
	 */
	void save(MooLbsStation mooLbsStation);
	
	/**
	 * 通过mooLbsStation删除一条记录
	 * @param mooLbsStation
	 */
	void delete(String iD);
	
	/**
	 * 修改记录
	 * @param mooLbsStation
	 */
	void update(MooLbsStation mooLbsStation);
}