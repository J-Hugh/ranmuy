package com.neo.admin.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.neo.admin.system.modular.sysresource.domain.MooSysResource;
import com.neo.admin.system.modular.sysresource.service.MooSysResourceService;
import com.neo.admin.system.modular.sysrole.domain.MooSysRole;

public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private MooSysResourceService mooSysResourceServiceImpl;
	
    private Map<String, Collection<ConfigAttribute>> resourceMap = null;
    public void loadResourceDefine()throws Exception  {
    	resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 
        List<MooSysResource> sources=mooSysResourceServiceImpl.findAll(true);
        if(sources!=null){	
        	for(MooSysResource source : sources){
        		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>(); 
        		for(MooSysRole role : source.getRoleList()){
        			ConfigAttribute ca = new SecurityConfig(role.getRoleCode());
    				atts.add(ca);
        		}
        		atts.add(new SecurityConfig("KSDL7DWQLK123L"));
        		resourceMap.put(source.getResources(), atts);
        	}
        }
        //System.out.println(new Gson().toJson(sources));
    }
    //根据一个网址，找到该网址的权限配置。
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {  
        
        FilterInvocation filterInvocation =(FilterInvocation) object;
        Iterator<String> ite = resourceMap.keySet().iterator();  
        while (ite.hasNext()) {
            String requestURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(requestURL);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(requestURL);
            }
        }
        return null;
    }
    public boolean supports(Class<?> clazz) {
        return true;
    }
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
        return null;
    }
	
}
