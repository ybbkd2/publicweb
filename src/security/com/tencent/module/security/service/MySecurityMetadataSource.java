/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 *
 * @author guoxp
 */

@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    //由spring调用
    @Autowired
    private PrivilegeService privilegeService;
    
    Thread updateThread = null;
    boolean started = false;

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

        System.out.println("----------MySecurityMetadataSource getAttributes -----------------");
        Collection<ConfigAttribute> val = null;
        FilterInvocation fi = ((FilterInvocation) object);
        String requestUrl = fi.getRequestUrl();
        System.out.println("requestUrl is " + requestUrl);
        if (resourceMap == null) {
            loadResourcePrivilegeDefine();
        }
        Set<String> urls = resourceMap.keySet();
        Iterator<String> murl = urls.iterator();
        while (murl.hasNext()) {
            String privURL = murl.next();
            AntPathRequestMatcher urlMatcher = new AntPathRequestMatcher(privURL);
            if (urlMatcher.matches(fi.getRequest())) {
                val = resourceMap.get(privURL);
                System.out.println(urlMatcher.getPattern() + " 匹配 。。。");
                System.out.println(val.size());
                break;
            }
        }

        //if (val == null) {
//            val= new ArrayList<ConfigAttribute>();
//            val.add(new SecurityConfig("ROLE_NO_USER"));
       // }

        return val;

    }
    
    private void loadResourcePrivilegeDefine() {
        resourceMap = privilegeService.loadResourceDefine();
        this.started = true;
        updateThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (started) {
                    
                    try {
                        Thread.sleep(1000L * 60 * 5 );
                    } catch (InterruptedException ex) {
                    }
                    
                    resourceMap = privilegeService.loadResourceDefine();
                }
            }
            
        });
        
        this.updateThread.setDaemon(true);
        this.updateThread.start();
    }

    //加载所有资源与权限的关系
}
