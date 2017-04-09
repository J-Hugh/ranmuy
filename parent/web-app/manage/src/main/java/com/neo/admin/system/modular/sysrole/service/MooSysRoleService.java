package com.neo.admin.system.modular.sysrole.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neo.admin.common.tools.Sequence;
import com.neo.admin.common.tools.SpringContextUtil;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.system.modular.sysrole.dao.MooSysRoleMapper;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;
import com.neo.admin.system.security.InvocationSecurityMetadataSourceService;

@Service
public class MooSysRoleService {

	@Autowired
	private MooSysRoleMapper mooSysRulesDao;
	
	@Autowired
	private SpringContextUtil springContextUtil;
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public BootstrapTable<MooSysRole> searchByPage(Map<String,Object> searchParams) throws Exception {
		List<MooSysRole> roles = mooSysRulesDao.searchByPage(searchParams);
		Integer total = mooSysRulesDao.total(searchParams);
		return new BootstrapTable<MooSysRole>(total, roles);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooSysRole> search(Map<String,Object> searchParams) throws Exception  {
		return (List<MooSysRole>) mooSysRulesDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<MooSysRole> findAll() throws Exception {
		return (List<MooSysRole>) mooSysRulesDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public MooSysRole findById(String rulesId) throws Exception {
		List<MooSysRole> mooSysRules = mooSysRulesDao.findById(rulesId);
		if(mooSysRules != null && mooSysRules.size()>0){
			return mooSysRules.get(0); 
		}
		return null;
	}

	/**
	 * 保存一条记录
	 * @param mooSysRules
	 */
	public void save(MooSysRole mooSysRules) throws Exception {
		
		mooSysRules.setCreDate(new Timestamp(System.currentTimeMillis()));
		mooSysRules.setRoleId(Sequence.getSequence().nextId()+"");
		mooSysRules.setRoleCode(mooSysRules.getRoleCode().toUpperCase());
		mooSysRulesDao.save(mooSysRules);
	}
	
	/**
	 * 保存角色和资源关联关系
	 * @param map
	 * @throws Exception
	 */
	@Transactional
	public void saveResourceRoles(Set<String> resoursId,String roleId) throws Exception {
		
		mooSysRulesDao.deleteRelationByRole(roleId);
		
		for(String s : resoursId){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("relationId", Sequence.getSequence().nextId()+"");
			map.put("resourceId", s);
			map.put("roleId", roleId);
			mooSysRulesDao.saveResourceRole(map);
		}

		//从新加载资源与角色的关系
		InvocationSecurityMetadataSourceService xx=(InvocationSecurityMetadataSourceService) springContextUtil.getBean("MySecurityMetadataSource");
		xx.loadResourceDefine();
	}
	
	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String rulesId) throws Exception {
		mooSysRulesDao.delete(rulesId);

	}
	
	/**
	 * 修改记录
	 * @param mooSysRules
	 */
	public void update(MooSysRole mooSysRules) throws Exception {
		mooSysRulesDao.update(mooSysRules);
	}
}
