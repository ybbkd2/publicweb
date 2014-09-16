/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security;

import com.tencent.framework.util.SpringContextHolder;
import com.tencent.module.security.service.PrivilegeService;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

 
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
//                    Session ses = null;
//                    SessionFactory sf = SpringContextHolder.getBean(SessionFactory.class);
                    try {
                        Thread.sleep(1000L * 10);
//                        ses = sf.openSession();
//                        TransactionSynchronizationManager.bindResource(sf, new SessionHolder(ses));
                        resourceMap = privilegeService.loadResourceDefine();
                    } catch (Exception ex) {
                        log.error("", ex);
                    } finally {
//                        TransactionSynchronizationManager.unbindResource(sf);
//                        if (ses !=null) {
//                            ses.close();
//                        }
                    }
                }
            }
        });
        this.updateThread.setDaemon(true);
        this.updateThread.start();
    }

    //加载所有资源与权限的关系
}
