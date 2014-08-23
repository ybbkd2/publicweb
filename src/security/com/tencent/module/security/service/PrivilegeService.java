/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import com.tencent.module.security.dao.PrivilegeDao;
import com.tencent.module.security.entity.Privilege;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

/**
 *
 * @author guoxp
 */
@Service
public class PrivilegeService {

    @Autowired
    PrivilegeDao privilegeDao;

    public Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
        Map<String, Collection<ConfigAttribute>> resourceMap = null;

        if (resourceMap == null) {
            System.out.println("loadResourceDefine ...");
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Privilege> resources = this.privilegeDao.list();
            for (Privilege resource : resources) {
                if (resource.getMatchUrl() != null) {
                    Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                    //以权限名封装为Spring的security Object
                    ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getId() );
                    configAttributes.add(configAttribute);
                    resourceMap.put(resource.getMatchUrl(), configAttributes);
                    System.out.println("Privilege URL:" + resource.getMatchUrl());
                }
            }
        }
        
        return resourceMap;
    }

}
