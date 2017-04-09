package com.neo.admin.system.modular.sysresource.domain;

import java.util.List;

import com.neo.admin.system.modular.sysrole.domain.MooSysRole;

public class MooSysResource {
    
    /**
      * 
      **/
	private String resourceId;
    /**
      * 资源名称
      **/
	private String resourceName;
    /**
      * 对应资源
      **/
	private String resources;
    /**
      * 属性
      **/
	private String attribute;
    /**
      * 父资源ID
      **/
	private String pResourceId;
    /**
      * 是否是菜单
      **/
	private String ismenu;
	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 访问该资源的角色
	 */
	private List<MooSysRole> roleList;
	
	private List<MooSysResource> children;
	
	public MooSysResource() {
		super();
	}

	public MooSysResource(String resourceId, String resourceName, String resources, String attribute,
			String pResourceId, String ismenu) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resources = resources;
		this.attribute = attribute;
		this.pResourceId = pResourceId;
		this.ismenu = ismenu;
	}

	

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MooSysResource> getChildren() {
		return children;
	}

	public void setChildren(List<MooSysResource> children) {
		this.children = children;
	}

	public List<MooSysRole> getRoleList() {
		return roleList;
	}



	public void setRoleList(List<MooSysRole> roleList) {
		this.roleList = roleList;
	}



	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResources() {
		return this.resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}
	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getpResourceId() {
		return this.pResourceId;
	}

	public void setpResourceId(String pResourceId) {
		this.pResourceId = pResourceId;
	}
	public String getIsmenu() {
		return this.ismenu;
	}

	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}
}