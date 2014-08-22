/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import com.tencent.module.security.dao.PrivilegeDao;
import com.tencent.module.security.entity.Privilege;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;

/**
 *
 * @author guoxp
 */
 //1 加载资源与权限的对应关系
@Transactional
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    //由spring调用
    
    

    private PrivilegeDao privilegeDao;

    public MySecurityMetadataSource() {

    }

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

    //返回所请求资源所需要的权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        Collection<ConfigAttribute> val = null;
        FilterInvocation fi = ((FilterInvocation) object);
        String requestUrl = fi.getRequestUrl();
        System.out.println("requestUrl is " + requestUrl);
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Set<String> urls = resourceMap.keySet();
        Iterator<String> murl = urls.iterator();
        while (murl.hasNext()) {
            AntPathRequestMatcher urlMatcher = new AntPathRequestMatcher(murl.next());
            if (urlMatcher.matches(fi.getRequest())) {
                val = resourceMap.get(murl);
                System.out.println(urlMatcher.getPattern()  + " 匹配 。。。");
                break;
            }
        }
        
        if (val!=null) {
            
        }
        
        return val;
        
        
    }

    //加载所有资源与权限的关系
    private void loadResourceDefine() {
        if (resourceMap == null) {
            System.out.println("loadResourceDefine ...");
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Privilege> resources = this.getPrivilegeDao().list();
            for (Privilege resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                //以权限名封装为Spring的security Object
                ConfigAttribute configAttribute = new SecurityConfig(resource.getName());
                configAttributes.add(configAttribute);
                resourceMap.put(resource.getMatchUrl(), configAttributes);
            }
        }

        Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
        Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();

    }

    /**
     * @return the privilegeDao
     */
    public PrivilegeDao getPrivilegeDao() {
        return privilegeDao;
    }

    /**
     * @param privilegeDao the privilegeDao to set
     */
    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }
}
