package com.neo.admin.system.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.admin.common.value.ActResult;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.common.value.BootstrapTree;
import com.neo.admin.common.value.JstreeNode;
import com.neo.admin.system.controller.BaseController;
import com.neo.admin.system.facade.ISystemFacade;
import com.neo.admin.system.modular.sysresource.domain.MooSysResource;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;
import com.neo.admin.system.modular.sysuser.domain.MooSysUser;

/**
 * 系统管理
 * @author neo
 *
 */
@Controller
@RequestMapping(value = "system")
public class SystemController extends BaseController {

	@Autowired
	private ISystemFacade systemFacade;
	
	//菜单树
	@RequestMapping(value = "menu_tree", method = RequestMethod.GET)
	@ResponseBody
	public List<BootstrapTree> menuTree() {
		return systemFacade.menuTree();
	}
	
	//资源树
	@RequestMapping(value = "resource_tree", method = RequestMethod.GET)
	@ResponseBody
	public List<JstreeNode> resourceTree() {
		return systemFacade.resourceTree();
	}
	
	//某权限拥有的资源
	@RequestMapping(value = "have_resource", method = RequestMethod.GET)
	@ResponseBody
	public List<String> resource(String roleId)throws Exception {
		return systemFacade.haveResource(roleId);
	}
	
	
	//某菜单下的功能点
	@RequestMapping(value = "functions", method = RequestMethod.GET)
	@ResponseBody
	public List<MooSysResource> functions(String resourceId) throws Exception{
		return systemFacade.functions(resourceId);
	}
	
	//注册新的菜单
	@RequestMapping(value = "append_menu", method = RequestMethod.POST)
	@ResponseBody
	public ActResult<Object> appendMenu(MooSysResource menu) throws Exception{
		ActResult<Object> ret = new ActResult<Object>();
		
		String msg = (systemFacade.appendMenu(menu));
		super.handleRet(ret, msg);
		return ret;
	}
	//删除菜单
	@RequestMapping(value = "del_menu", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Object> delMenu(String menuId) throws Exception{
		ActResult<Object> ret = new ActResult<Object>();
		
		String msg = systemFacade.delMenu(menuId);
		super.handleRet(ret, msg);
		return ret;
	}
	
	//搜索用户
	@RequestMapping(value = "users", method = RequestMethod.GET)
	@ResponseBody
	public BootstrapTable<MooSysUser> users(SystemUserSearch search) throws Exception{
		return systemFacade.users(this.objToHash(search));
	}
	//搜索角色
	@RequestMapping(value = "roles", method = RequestMethod.GET)
	@ResponseBody
	public BootstrapTable<MooSysRole> roles(SystemUserSearch search) throws Exception{
		
		return systemFacade.roles(this.objToHash(search));
	}
	
	//编辑角色权限
	@RequestMapping(value = "edit_roles_resource", method = RequestMethod.POST)
	@ResponseBody
	public ActResult<Object> editRolesResource(String resourcesIds,String roleId) throws Exception{
		
		ActResult<Object> ret = new ActResult<Object>();
		String msg=systemFacade.editRoleResource(resourcesIds, roleId);
		super.handleRet(ret, msg);
		
		return ret;
	}
	
	
	@RequestMapping(value = "append_user", method = RequestMethod.POST)
	@ResponseBody
	public ActResult<Object> appendUser(MooSysUser user) throws Exception{
		ActResult<Object> ret = new ActResult<Object>();
		
		if(user == null){
			super.handleRet(ret, "表单错误");
			return ret;
		}
		if(StringUtils.isEmpty(user.getuName())){
			super.handleRet(ret, "用户姓名不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(user.getuAccount())){
			super.handleRet(ret, "登陆账号不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(user.getuPassword())){
			super.handleRet(ret, "密码不能为空");
			return ret;
		}
		if(user.getuPassword().length()<6){
			super.handleRet(ret, "密码太短啦亲...");
			return ret;
		}
		if(StringUtils.isEmpty(user.getRoleId())){
			super.handleRet(ret, "请设置权限哟(⊙o⊙)");
			return ret;
		}
		String msg = systemFacade.appendUser(user);
		super.handleRet(ret, msg);
		return ret;
	}
	@RequestMapping(value = "edit_user", method = RequestMethod.POST)
	@ResponseBody
	public ActResult<Object> editUser(MooSysUser user) throws Exception{
		ActResult<Object> ret = new ActResult<Object>();
		
		if(user == null){
			super.handleRet(ret, "表单错误");
			return ret;
		}
		if(StringUtils.isEmpty(user.getuName())){
			super.handleRet(ret, "用户姓名不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(user.getRoleId())){
			super.handleRet(ret, "请设置权限哟(⊙o⊙)");
			return ret;
		}
		String msg = systemFacade.editUser(user);
		super.handleRet(ret, msg);
		return ret;
	}
	@RequestMapping(value = "del_user", method = RequestMethod.GET)
	@ResponseBody
	public ActResult<Object> delUser(String uId) throws Exception{
		ActResult<Object> ret = new ActResult<Object>();
		String msg = systemFacade.delUser(uId);
		super.handleRet(ret, msg);
		return ret;
	}
	
	@RequestMapping(value = "append_role", method = RequestMethod.POST)
	@ResponseBody
	public ActResult<MooSysRole> appendRole(MooSysRole role) throws Exception{
		ActResult<MooSysRole> ret = new ActResult<MooSysRole>();
		
		if(role == null){
			super.handleRet(ret, "表单错误");
			return ret;
		}
		if(StringUtils.isEmpty(role.getRoleName())){
			super.handleRet(ret, "角色名不能为空");
			return ret;
		}
		if(StringUtils.isEmpty(role.getRoleCode())){
			super.handleRet(ret, "角色编码不能为空");
			return ret;
		}
		role.setCreName(super.getLogingUser().getUsername());
		ret = systemFacade.appendRole(role);
		
		return ret;
	}
	
	//省市县
//	@RequestMapping(value = "areaTree", method = RequestMethod.GET)
//	@ResponseBody
//	public List<MooSysArea> areaTree() throws Exception{
//		
//		List<MooSysArea> areas = systemFacade.areaTree();
//		
//		return areas;
//	}
	
	
}
