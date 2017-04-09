package com.neo.admin.system.modular.sysresource.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.Sequence;
import com.neo.admin.common.value.BootstrapTree;
import com.neo.admin.common.value.JstreeNode;
import com.neo.admin.system.modular.sysresource.dao.MooSysResourceMapper;
import com.neo.admin.system.modular.sysresource.domain.MooSysResource;
import com.neo.admin.system.modular.sysrole.dao.MooSysRoleMapper;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;

@Service
public class MooSysResourceService {

	@Autowired
	private MooSysResourceMapper mooSysResourceDao;
	@Autowired
	private MooSysRoleMapper mooSysRulesMapper;
	
	
	/**
	 * 菜单树
	 * @return
	 */
	public List<BootstrapTree> menuTree(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ismenu", "Y");
		params.put("pResourceId", "0");
		List<MooSysResource> sources=mooSysResourceDao.search(params);
		
		List<BootstrapTree> menus=new ArrayList<BootstrapTree>();
		for(MooSysResource s : sources){
			BootstrapTree t=this.s2t(s);
			menus.add(t);
			this.children(t);
		}
		return menus;
	}
	
	/**
	 * 递归获取菜单树
	 * @param root
	 */
	private void children(BootstrapTree root){
		if(root != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ismenu", "Y");
			params.put("pResourceId", root.getResourcesId());
			List<MooSysResource> sources=mooSysResourceDao.search(params);
			
			List<BootstrapTree> nodes=new ArrayList<BootstrapTree>();
			for(MooSysResource s: sources){
				BootstrapTree t=this.s2t(s);
				nodes.add(t);
				this.children(t);
			}
			root.setNodes(nodes.isEmpty() ? null : nodes);
		}
	}
	private BootstrapTree s2t(MooSysResource s){
		BootstrapTree t=new BootstrapTree();
		t.setResourcesId(s.getResourceId());
		t.setText(s.getResourceName());
		t.setUrl(s.getResources());
		return t;
	}
	
	/**
	 * 资源树
	 * @return
	 */
	public List<JstreeNode> resourceTree() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pResourceId", "0");
		List<MooSysResource> sources = mooSysResourceDao.search(params);
		
		List<JstreeNode> menus = new ArrayList<JstreeNode>();
		for (MooSysResource s : sources) {
			JstreeNode t = this.s2j(s);
			menus.add(t);
			this.children(t);
		}
		return menus;
	}
	
	/**
	 * 递归获取资源树
	 * @param root
	 */
	private void children(JstreeNode root){
		if(root != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pResourceId", root.getId());
			List<MooSysResource> sources=mooSysResourceDao.search(params);
			List<JstreeNode> nodes=new ArrayList<JstreeNode>();
			for(MooSysResource s: sources){
				JstreeNode t=this.s2j(s);
				nodes.add(t);
				this.children(t);
			}
			if(!nodes.isEmpty()){
				root.getState().put("opened", true);
			}
			root.setChildren(nodes.isEmpty() ? null : nodes);
		}
	}
	private JstreeNode s2j(MooSysResource s){
		JstreeNode j=new JstreeNode();
		j.setId(s.getResourceId());
		j.setText(s.getResourceName());
		//j.setIcon("Y".equals(s.getIsmenu()) ? "glyphicon glyphicon-menu-hamburger" : "glyphicon glyphicon-record");
		j.setIcon("none");
		return j;
	}
	
	/**
	 * 通过角色查询资源ID
	 * @return
	 */
	public List<String> findByRoleId(String roleId){
		return mooSysResourceDao.findByRoleId(roleId);
	}
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooSysResource> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<MooSysResource>) mooSysResourceDao.searchByPage(searchParams);
	}

	/**
	 * 根据资源ids查询
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<MooSysResource> findByResourceIds(Object[] ids ) throws Exception {
		return (List<MooSysResource>) mooSysResourceDao.findByResourceIds(ids);
	}
	
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<MooSysResource> search(Map<String,Object> searchParams) throws Exception  {
		return (List<MooSysResource>) mooSysResourceDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<MooSysResource> findAll(Boolean haveRules) throws Exception {
		
		List<MooSysResource> sources=mooSysResourceDao.findAll();
		
		if(!haveRules){
			return sources;
		}
		
		for(int i=0;i<sources.size();i++){
			String resourceId=sources.get(i).getResourceId();
			List<MooSysRole> role=mooSysRulesMapper.findByResource(resourceId);
			sources.get(i).setRoleList(role);
		}
		return sources;
	}
	public List<MooSysResource> findAllMenu() throws Exception {
		
		List<MooSysResource> sources=mooSysResourceDao.findAllMenu();
		return sources;
	}
	/**
	 * 通过获取一条记录
	 * @param  主键
	 * @return
	 */
	public MooSysResource findById(String resourceId) throws Exception {
		MooSysResource mooSysResource = mooSysResourceDao.findById(resourceId);
		if(mooSysResource == null){
			mooSysResource = new MooSysResource();
		}
		return mooSysResource;
	}

	/**
	 * 保存一条记录
	 * @param mooSysResource
	 */
	public void save(MooSysResource mooSysResource) throws Exception {
		mooSysResource.setResourceId(Sequence.getSequence().nextId()+"");
		mooSysResourceDao.save(mooSysResource);
	}

	/**
	 * 通过  删除一条记录
	 * @param  主键
	 */
	public void delete(String resourceId) throws Exception {
		List<MooSysResource> rs = mooSysResourceDao.findByPId(resourceId);
		if(rs != null && !rs.isEmpty()){
			mooSysResourceDao.delByPId(resourceId);
			for(MooSysResource r : rs){
				//递归删除下级节点
				delete(r.getResourceId());
			}
		}
		mooSysResourceDao.delete(resourceId);
	}
	
	/**
	 * 修改记录
	 * @param mooSysResource
	 */
	public void update(MooSysResource mooSysResource) throws Exception {
		mooSysResourceDao.update(mooSysResource);
	}
	
	/**
	 * 
	 * @param rIds
	 * @return
	 * @throws Exception
	 */
	public List<MooSysResource> findByRids(String [] rIds) throws Exception {
		return mooSysResourceDao.findByRoleIds(rIds);
	}
}
