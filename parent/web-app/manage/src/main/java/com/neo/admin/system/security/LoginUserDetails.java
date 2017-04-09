package com.neo.admin.system.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.neo.admin.system.modular.sysuser.domain.MooSysUser;

public class LoginUserDetails extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5780050442382102211L;
	
	private MooSysUser user;
	
	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public LoginUserDetails(String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,MooSysUser user) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

		this.user = user;
	}

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public LoginUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public LoginUserDetails(String username, String password) {
		super(username, password, new ArrayList<GrantedAuthority>());
	}


	
	
	
	

	public MooSysUser getUser() {
		return user;
	}

	public void setUser(MooSysUser user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object rhs) {
		// TODO Auto-generated method stub
		return super.equals(rhs);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
