/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import com.tencent.module.security.MySecurityMetadataSource;
import com.tencent.module.security.dao.PrivilegeDao;
import com.tencent.module.security.dao.RoleDao;
import com.tencent.module.security.dao.UserDao;
import com.tencent.module.security.entity.Privilege;
import com.tencent.module.security.entity.Role;
import com.tencent.module.security.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author guoxp
 */
@Service
public class PrivilegeService {

    private static final Log log = LogFactory.getLog(MySecurityMetadataSource.class);

    @Autowired
    PrivilegeDao privilegeDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    public Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
        Map<String, Collection<ConfigAttribute>> resourceMap = null;

        if (resourceMap == null) {
            log.debug("loadResourceDefine ...");
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Privilege> resources = this.privilegeDao.list();
            for (Privilege resource : resources) {
                if (resource.getMatchUrl() != null) {
                    Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                    //以权限名封装为Spring的security Object
                    ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getId());
                    configAttributes.add(configAttribute);
                    resourceMap.put(resource.getMatchUrl(), configAttributes);
                    log.debug("Privilege URL:" + resource.getMatchUrl());
                }
            }
        }

        return resourceMap;
    }

    public Map<Long, Privilege> getMyPrivileges(long parentId) {
        
        String username =null;
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails) principal).getUsername();
        } else {
             username = principal.toString();
        }

        User user = userDao.uniqueResult("username", username);
        Set<Role> roles = user.getGrantedRoles();

        TreeMap<Long, Privilege> pr = new TreeMap<Long, Privilege>();

        for (Role role : roles) {
            for (Privilege p : role.getPrivileges()) {
                if (parentId == 0 && p.getParent() == null) {
                    pr.put(p.getId(), p);
                } else if (p.getParent()!=null && parentId == p.getParent().getId()) {
                    pr.put(p.getId(), p);
                }
            }
        }

        return pr;
    }

}
