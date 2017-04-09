package com.neo.admin.system.modular.sysuser.domain;

import java.util.ArrayList;
import java.util.List;

import com.neo.admin.system.modular.sysrole.domain.MooSysRole;

public class MooSysUser {
    
    /**
      * 
      **/
	private String uId;
    /**
      * 账号
      **/
	private String uAccount;
    /**
      * 密码
      **/
	private String uPassword;
    /**
      * 用户名称
      **/
	private String uName;
	/**
	 * qq号
	 */
	private String qqNumber;
	/**
	 * 手机号
	 */
	private String phoneNumber;
	
    /**
      * 注册时间
      **/
	private java.sql.Timestamp regDate;
	/**
	 * 状态
	 * 01--正常
	 * 02--锁定
	 */
	private String uState;

	/**
	 * 角色ID
	 */
	private String roleId; 
	
	/**
	 * 角色名
	 */
	private String roleName;
	
	/**
	 * 拥有的权限集
	 */
	private List<MooSysRole> roles = new ArrayList<>();
	
	public MooSysUser() {
		super();
	}
	
	public MooSysUser(String uName) {
		super();
		this.uName = uName;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public List<MooSysRole> getRoles() {
		return roles;
	}


	public void setRoles(List<MooSysRole> roles) {
		this.roles = roles;
	}


	public String getQqNumber() {
		return qqNumber;
	}


	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getuState() {
		return uState;
	}

	public void setuState(String uState) {
		this.uState = uState;
	}


	public String getuId() {
		return this.uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuAccount() {
		return this.uAccount;
	}

	public void setuAccount(String uAccount) {
		this.uAccount = uAccount;
	}
	public String getuPassword() {
		return this.uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuName() {
		return this.uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}
	public java.sql.Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(java.sql.Timestamp regDate) {
		this.regDate = regDate;
	}

}