package com.neo.admin.system.modular.sysrole.domain;

public class MooSysRole {
    
    /**
      * 
      **/
	private String roleId;
    /**
      * 权限名称名称
      **/
	private String roleName;
    /**
      * 编码
      **/
	private String roleCode;
    /**
      * 父权限ID
      **/
	private String pRoleId;
	/**
	 * 人数
	 */
	private Integer number;
	/**
	 * 创建人名字
	 */
	private String creName;
	/**
	 * 创建日期
	 */
	private java.sql.Timestamp creDate;
	
	/**
	 * 创建一个
	 * @param rulesName
	 * @param rulesCode
	 */
	public MooSysRole(String rulesCode){
		super();
		this.roleId = "rulesCode";
		this.roleName = "rulesCode";
		this.roleCode = rulesCode;
		this.pRoleId = "rulesCode";
	}

	public MooSysRole() {
		super();
	}

	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCreName() {
		return creName;
	}

	public void setCreName(String creName) {
		this.creName = creName;
	}

	public java.sql.Timestamp getCreDate() {
		return creDate;
	}

	public void setCreDate(java.sql.Timestamp creDate) {
		this.creDate = creDate;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getpRoleId() {
		return pRoleId;
	}

	public void setpRoleId(String pRoleId) {
		this.pRoleId = pRoleId;
	}

}