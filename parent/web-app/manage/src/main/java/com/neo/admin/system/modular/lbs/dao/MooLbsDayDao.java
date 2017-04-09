package com.neo.admin.system.modular.lbs.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.lbs.domain.MooLbsDay;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface MooLbsDayDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooLbsDay> searchByPage(Map<String,Object> searchParams);
	Integer total (Map<String,Object> searchParams);
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooLbsDay> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<MooLbsDay> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	MooLbsDay findById(String iD);
	
	/**
	 * 保存一条记录
	 * @param mooLbsDay
	 */
	void save(MooLbsDay mooLbsDay);
	
	/**
	 * 通过mooLbsDay删除一条记录
	 * @param mooLbsDay
	 */
	void delete(String iD);
	
	void deleteByDate(String date);
	/**
	 * 修改记录
	 * @param mooLbsDay
	 */
	void update(MooLbsDay mooLbsDay);
	/**
	 * 修改系统判定线路
	 * @param mooLbsDay
	 */
	void updateJudgeLineName(MooLbsDay mooLbsDay);
}