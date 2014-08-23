/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author guoxp
 */


public class MyAccessDecisionManager implements AccessDecisionManager {

   private static final Log log = LogFactory.getLog(MyAccessDecisionManager.class);
    

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        
        log.debug("MyAccessDecisionManager decide ...");
        
        if (configAttributes == null) {
            log.debug("no configAttributes ... ");
            return;
        }
        //所请求的资源拥有的权限(一个资源对多个权限)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            log.debug("needPermission is " + needPermission);
            //用户所拥有的权限authentication
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                System.out.println("list:" + ga.getAuthority());
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限
        throw new AccessDeniedException(" 没有权限访问！ ");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}
