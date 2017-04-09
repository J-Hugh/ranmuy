package com.neo.admin.system.facade;

import java.util.List;
import java.util.Map;

import com.neo.admin.common.value.ActResult;
import com.neo.admin.common.value.BootstrapTable;
import com.neo.admin.common.value.BootstrapTree;
import com.neo.admin.common.value.JstreeNode;
import com.neo.admin.system.modular.sysresource.domain.MooSysResource;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;
import com.neo.admin.system.modular.sysuser.domain.MooSysUser;

/**
 * 系统相关facade
 * @author luoyulin
 *
 */
public interface ISystemFacade {

	/**
	 * 通过JOSN数据获取一个permit实例
	 * @param permit
	 * @param content
	 * @return
	 */
	//public Permit instanceJson(String permit,String content) throws Exception;
	/**
	 * 通过XML数据获取一个permit实例
	 * @param permit
	 * @param content
	 * @return
	 */
	//public Permit instanceXml(String permit,String content) throws Exception;
	
	/**
	 * 菜单树
	 * @return
	 * @throws Exception
	 */
	public List<BootstrapTree> menuTree();
	
	/**
	 * 资源树
	 * @return
	 */
	public List<JstreeNode> resourceTree();
	
	/**
	 * 某菜单下所有的功能点
	 * @param resourceId
	 * @return
	 */
	public List<MooSysResource> functions(String resourceId)  throws Exception;
	
	
	/**
	 * 某角色拥有的资源ID
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<String> haveResource(String roleId) throws Exception; 
	
	/**
	 * 注册菜单
	 * @param menu
	 * @return
	 */
	public String appendMenu(MooSysResource menu) throws Exception;
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	public String delMenu(String menuId) throws Exception;
	/**
	 * 注册功能点
	 * @param function
	 * @return
	 */
	public String appendFunction(MooSysResource function) throws Exception;
	
	/**
	 * 搜索系统用户
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BootstrapTable<MooSysUser> users(Map<String, Object> map) throws Exception;
	
	/**
	 * 管理员登陆
	 * @param uAccount
	 * @return
	 * @throws Exception
	 */
	public MooSysUser login(String uAccount)throws Exception;
	
	/**
	 * 搜索角色
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BootstrapTable<MooSysRole> roles(Map<String, Object> map) throws Exception;
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String appendUser(MooSysUser user) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String editUser(MooSysUser user) throws Exception;
	/**
	 * 删除用户
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	public String delUser(String uId) throws Exception;
	/**
	 * 新增角色
	 * @return
	 * @throws Exception
	 */
	public ActResult<MooSysRole> appendRole(MooSysRole role) throws Exception;
	
	/**
	 * 编辑角色拥有的资源
	 * @param resources
	 * @param roleId
	 * @return
	 */
	public String editRoleResource(String resources,String roleId) throws Exception;
	
	/**
	 * 页面展示的菜单树
	 * @param roleIds
	 * @return
	 * @throws Exception
	 */
	public List<MooSysResource> menuTree(String [] roleIds)  throws Exception;
	
	/**
	 * 系统所有角色
	 * @return
	 * @throws Exception
	 */
	public List<MooSysRole> roles() throws Exception;
	/**
	 * 地址树
	 * @return
	 * @throws Exception
	 */
	//public List<MooSysArea> areaTree() throws Exception;
}
