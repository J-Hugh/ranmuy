package com.neo.admin.system.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.neo.admin.system.security.handler.CustomLogoutHandler;  
@Service("AccessDecisionManager")  
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {  
    /** 
     * Logger for this class 
     */  
	private static Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);
    
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {  
        if (configAttributes == null) {  
            return;  
        }
        logger.info("正在访问的url是："+object.toString()) ;  
        Iterator<ConfigAttribute> ite = configAttributes.iterator();  
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();  
            logger.info("需要权限："+ca.getAttribute());  
            String needRole = ((SecurityConfig) ca).getAttribute();  
            for (GrantedAuthority ga : authentication.getAuthorities()) {  
                logger.info("/t授权信息是："+ga.getAuthority());  
                if (needRole.equals(ga.getAuthority())) {
                    logger.info("判断到，needRole 是"+needRole+",用户的角色是:"+ga.getAuthority()+"，授权数据相匹配");  
                    return;  
                }
            }
        }
        throw new AccessDeniedException("没有权限");  
    }  
    public boolean supports(ConfigAttribute attribute) {
        return true;  
    }  
    public boolean supports(Class<?> clazz) {  
        return true;  
    }  
}   