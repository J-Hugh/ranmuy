package com.neo.espionage.modular.wechatRecord.dao;

import com.neo.espionage.modular.wechatRecord.domain.WechatRecord;

import java.util.List;
import java.util.Map;



/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */

public interface WechatRecordDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<WechatRecord> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<WechatRecord> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<WechatRecord> findAll();
	
	/**
	 * 通过 id 获取一条记录
	 * @param id 主键
	 * @return
	 */
	WechatRecord findById(String id);
	
	/**
	 * 保存一条记录
	 * @param wechatRecord
	 */
	void save(WechatRecord wechatRecord);
	
	/**
	 * 通过wechatRecord删除一条记录
	 * @param wechatRecord
	 */
	void delete(String id);
	
	/**
	 * 修改记录
	 * @param wechatRecord
	 */
	void update(WechatRecord wechatRecord);
}