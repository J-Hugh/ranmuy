package com.neo.admin.system.modular.sysresource.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.sysresource.domain.MooSysResource;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface MooSysResourceMapper {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooSysResource> searchByPage(Map<String,Object> searchParams);
	Integer searchTotal(Map<String,Object> searchParams);
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooSysResource> search(Map<String,Object> searchParams);
	/**
	 * 根据上级菜单查询记录
	 * @param pId
	 * @return
	 */
	List<MooSysResource> findByPId(String pId);
	/**
	 * 根据上级查到删除记录
	 * @param pId
	 */
	void delByPId(String pId);
	/**
	 * 查询某权限拥有的资源ID
	 * @param roleId
	 * @return
	 */
	List<String> findByRoleId(String roleId);
	
	/**
	 * 根据权限IDS查询菜单
	 * @param ids
	 * @return
	 */
	List<MooSysResource> findByRoleIds(String [] ids);
	
	/**
	 * 根据资源IDS查询资源
	 * @param ids
	 * @return
	 */
	List<MooSysResource> findByResourceIds(Object [] ids);
	/**
	 * 获取所有记录
	 * @return
	 */
	List<MooSysResource> findAll();
	List<MooSysResource> findAllMenu(); 
	/**
	 * 通过 rESOURCE_ID 获取一条记录
	 * @param rESOURCE_ID 主键
	 * @return
	 */
	MooSysResource findById(String rESOURCE_ID);
	
	/**
	 * 保存一条记录
	 * @param mooSysResource
	 */
	void save(MooSysResource mooSysResource);
	
	/**
	 * 通过mooSysResource删除一条记录
	 * @param mooSysResource
	 */
	void delete(String rESOURCE_ID);
	
	/**
	 * 修改记录
	 * @param mooSysResource
	 */
	void update(MooSysResource mooSysResource);
}