package com.neo.admin.system.modular.sysrole.dao;

import java.util.List;
import java.util.Map;

import com.neo.admin.system.modular.sysrole.domain.MooSysRole;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface MooSysRoleMapper {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooSysRole> searchByPage(Map<String,Object> searchParams);
	Integer total(Map<String,Object> searchParams);
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<MooSysRole> search(Map<String,Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<MooSysRole> findAll();
	
	/**
	 * 查询某资源需要的权限
	 * @param resourceId
	 * @return
	 */
	List<MooSysRole> findByResource(String resourceId);
	
	/**
	 * 查询某用户拥有的权限
	 * @param uId
	 * @return
	 */
	List<MooSysRole> findByUser(String uId);
	
	/**
	 * 通过 rULES_ID 获取一条记录
	 * @param rULES_ID 主键
	 * @return
	 */
	List<MooSysRole> findById(String rULES_ID);
	
	/**
	 * 保存一条记录
	 * @param mooSysRules
	 */
	void save(MooSysRole mooSysRules);
	
	/**
	 * 保存资源和角色的关联关系
	 * @param map
	 */
	void saveResourceRole(Map<String, Object> map);
	
	/**
	 * 通过mooSysRules删除一条记录
	 * @param mooSysRules
	 */
	void delete(String rULES_ID);
	
	void deleteRelationByRole(String roleId);
	
	/**
	 * 修改记录
	 * @param mooSysRules
	 */
	void update(MooSysRole mooSysRules);
}