/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
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

    private static final Log log = LogFactory.getLog(MySecurityMetadataSource.class);
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

        log.debug("----------MySecurityMetadataSource getAttributes -----------------");
        Collection<ConfigAttribute> val = null;
        FilterInvocation fi = ((FilterInvocation) object);
        String requestUrl = fi.getRequestUrl();
        log.debug("requestUrl is " + requestUrl);
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
                log.debug(urlMatcher.getPattern() + " 匹配 。。。");
                break;
            }
        }

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
                        Thread.sleep(1000L * 60 * 5);
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
