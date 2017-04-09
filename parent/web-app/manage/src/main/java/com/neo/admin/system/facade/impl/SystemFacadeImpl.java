package com.neo.admin.system.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.neo.admin.common.value.ActResult;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.common.value.BootstrapTree;
import com.neo.admin.common.value.JstreeNode;
import com.neo.admin.system.facade.ISystemFacade;
import com.neo.admin.system.modular.sysresource.domain.MooSysResource;
import com.neo.admin.system.modular.sysresource.service.MooSysResourceService;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;
import com.neo.admin.system.modular.sysrole.service.MooSysRoleService;
import com.neo.admin.system.modular.sysuser.domain.MooSysUser;
import com.neo.admin.system.modular.sysuser.service.MooSysUserService;

@Service
public class SystemFacadeImpl implements ISystemFacade {

	// private static Gson gson=new Gson();

	// private XStream xs = new XStream(new DomDriver());

	// @Autowired
	// private MooSubsystemInfoService mooSubsystemInfoServiceImpl;
	@Autowired
	private MooSysResourceService mooSysResourceServiceImpl  ;
	@Autowired
	private MooSysUserService mooSysUserServiceImpl;
	@Autowired
	private MooSysRoleService mooSysRoleServiceImpl;
	// @Autowired
	// private MooSysAreaService mooSysAreaServiceImpl;

	// @Override
	// public Permit instanceJson(String permitJson, String content) throws
	// Exception {
	//
	// Permit permit=gson.fromJson(permitJson, Permit.class);
	// if(permit == null) throw new Exception("许可证信息错误");
	//
	// MooSubsystemInfo
	// info=mooSubsystemInfoServiceImpl.findByAppkey(permit.getAppKey());
	// if(info == null) throw new Exception("APPKEY 错误");
	//
	// if(!permit.verification(content, info.getSecretKey()))
	// throw new Exception("校验错误");
	//
	// return permit;
	// }

	// @Override
	// public Permit instanceXml(String permitXml, String content) throws
	// Exception {
	//
	// xs.alias("Permit", Permit.class);
	//
	// Permit permit=(Permit) xs.fromXML(permitXml);
	// if(permit == null) throw new Exception("许可证信息错误");
	//
	// MooSubsystemInfo
	// info=mooSubsystemInfoServiceImpl.findByAppkey(permit.getAppKey());
	// if(info == null) throw new Exception("APPKEY 错误");
	//
	// if(!permit.verification(content, info.getSecretKey()))
	// throw new Exception("校验错误");
	//
	// return permit;
	// }

	@Override
	public List<BootstrapTree> menuTree() {

		return mooSysResourceServiceImpl.menuTree();

	}

	@Override
	public List<JstreeNode> resourceTree() {

		return mooSysResourceServiceImpl.resourceTree();
	}

	@Override
	public List<MooSysResource> functions(String resourceId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pResourceId", resourceId);
		params.put("ismenu", "N");

		return mooSysResourceServiceImpl.search(params);
	}

	@Override
	public String appendMenu(MooSysResource menu) throws Exception {
		mooSysResourceServiceImpl.save(menu);
		return "SUCCESS";
	}

	@Override
	public String appendFunction(MooSysResource function) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pResourceId", function.getpResourceId());
		List<MooSysResource> resource = mooSysResourceServiceImpl.search(params);
		if (resource == null || resource.isEmpty()) {
			return "注册功能点失败  无效的上级菜单";
		}
		mooSysResourceServiceImpl.save(function);

		return "SUCCESS";
	}

	@Override
	public String appendUser(MooSysUser user) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uAccount", user.getuAccount());
		List<MooSysUser> users = mooSysUserServiceImpl.search(params);
		if (users != null && !users.isEmpty()) {
			return "已存在的登陆名 请换个试试";
		}

		mooSysUserServiceImpl.save(user);

		return "SUCCESS";
	}
	
	@Override
	public String editUser(MooSysUser user) throws Exception {
		MooSysUser du = mooSysUserServiceImpl.findById(user.getuId());
		if(du == null){
			return "你下手迟了 该用户已失效";
		}
		user.setuPassword(null);
		user.setuAccount(null);
		mooSysUserServiceImpl.update(user);
		return "SUCCESS";
	}

	@Override
	public BootstrapTable<MooSysUser> users(Map<String, Object> params) throws Exception {
		return mooSysUserServiceImpl.searchByPage(params);
	}

	@Override
	public BootstrapTable<MooSysRole> roles(Map<String, Object> params) throws Exception {
		return mooSysRoleServiceImpl.searchByPage(params);
	}

	@Override
	public List<String> haveResource(String roleId) throws Exception {
		return mooSysResourceServiceImpl.findByRoleId(roleId);
	}

	@Override
	@Transactional
	public String editRoleResource(String resources, String roleId) throws Exception {

		if (resources == null || StringUtils.isEmpty(resources)) {
			return "请选择资源";
		}
		String[] resourcesArry = resources.split("-");
		if (resourcesArry.length < 1) {
			return "请选择资源";
		}
		MooSysRole role = mooSysRoleServiceImpl.findById(roleId);
		if (role == null) {
			return "无效的角色 请刷新从试";
		}

		Set<String> idset = new HashSet<>();

		t(idset, resourcesArry);

		mooSysRoleServiceImpl.saveResourceRoles(idset, roleId);

		return "SUCCESS";
	}

	private void t(Set<String> idset, Object[] ids) throws Exception {
		Set<String> nrs = new HashSet<>();

		List<MooSysResource> rs = mooSysResourceServiceImpl.findByResourceIds(ids);

		for (MooSysResource r : rs) {
			idset.add(r.getResourceId());
			idset.add(r.getpResourceId());
			if (!"0".equals(r.getpResourceId())) {
				nrs.add(r.getpResourceId());
			}
		}
		if (nrs.size() > 0) {
			t(idset, nrs.toArray());
		}
	}

	@Override
	public MooSysUser login(String uAccount) throws Exception {
		return mooSysUserServiceImpl.findByAccount(uAccount);
	}

	@Override
	public List<MooSysResource> menuTree(String[] roleIds) throws Exception {
		
		List<MooSysResource> resources;
		
		if(roleIds == null || roleIds.length==0){
			resources = mooSysResourceServiceImpl.findAllMenu();
		}else{
			resources = mooSysResourceServiceImpl.findByRids(roleIds);
		}
		
		List<MooSysResource> tree = new ArrayList<>();

		while (resources.size() > 0) {
			Iterator<MooSysResource> it = resources.iterator();
			MooSysResource r = it.next();
			if ("0".equals(r.getpResourceId())) {
				it.remove();
				tree.add(r);
				t(it, r);
			}
		}
		return tree;
	}

	private void t(Iterator<MooSysResource> it, MooSysResource node) {
		List<MooSysResource> children = new ArrayList<>();

		while (it.hasNext()) {
			MooSysResource r = it.next();
			if (node.getResourceId().equals(r.getpResourceId())) {
				it.remove();
				children.add(r);
			}
		}
		node.setChildren(children);
	}

	@Override
	public List<MooSysRole> roles() throws Exception {

		return mooSysRoleServiceImpl.findAll();

	}

	@Override
	public ActResult<MooSysRole> appendRole(MooSysRole role) throws Exception {
		
		ActResult<MooSysRole> act = new ActResult<MooSysRole>();
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("roleCode", role.getRoleCode().toUpperCase());
		List<MooSysRole> ds = mooSysRoleServiceImpl.search(searchParams);
		if (ds != null && !ds.isEmpty()) {
			act.setFail("角色编码重复 重新设置一个吧...");
			return act;
		}
		
		mooSysRoleServiceImpl.save(role);
		act.setSuccess(role);
		return act;
	}

	@Override
	public String delUser(String uId) throws Exception {
		
		MooSysUser user = mooSysUserServiceImpl.findById(uId);
		if(user == null){
			return "你下手迟了 他已经被其他人干掉了";
		}
		mooSysUserServiceImpl.delete(uId);
		return "SUCCESS";
	}

	@Override
	public String delMenu(String menuId) throws Exception {
		mooSysResourceServiceImpl.delete(menuId);
		return "SUCCESS";
	}

	// @Override
	// public List<MooSysArea> areaTree() throws Exception {
	// return mooSysAreaServiceImpl.findTree();
	// }
}
