package com.neo.admin.system.modular.sysuser.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.sysuser.domain.MooSysUser;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface MooSysUserMapper {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooSysUser> searchByPage(Map<String,Object> searchParams);
	Integer total(Map<String,Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooSysUser> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<MooSysUser> findAll();
	
	/**
	 * 通过 u_ID 获取一条记录
	 * @param u_ID 主键
	 * @return
	 */
	MooSysUser findById(String u_ID);
	/**
	 * 根据账号查询
	 * @param uAccount
	 * @return
	 */
	MooSysUser findByAccount(String uAccount);
	/**
	 * 保存一条记录
	 * @param mooSysUser
	 */
	void save(MooSysUser mooSysUser);
	
	/**
	 * 通过mooSysUser删除一条记录
	 * @param mooSysUser
	 */
	void delete(String u_ID);
	/**
	 * 虚拟删除
	 * @param uId
	 */
	void virtualDel(String uId);
	
	/**
	 * 修改记录
	 * @param mooSysUser
	 */
	void update(MooSysUser mooSysUser);
}