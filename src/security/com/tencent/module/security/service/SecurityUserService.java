/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.service;

import com.tencent.module.security.dao.UserDao;
import com.tencent.module.security.entity.Privilege;
import com.tencent.module.security.entity.Role;
import com.tencent.module.security.entity.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class SecurityUserService implements UserDetailsService {

    private static final Log log = LogFactory.getLog(SecurityUserService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        log.debug("load user:" + name + " from DB.");
        User u = userDao.uniqueResult("username", name);

        if (u == null) {
            throw new UsernameNotFoundException("user:" + name + " not found.");
        }

        org.springframework.security.core.userdetails.User springUser
                = new org.springframework.security.core.userdetails.User(
                        u.getUsername(),
                        u.getPassword(),
                        true, // enabled
                        true, //accountNonExpired
                        true, //credentialsNonExpired
                        true, // accountNonLocked
                        getAuthorities(u));

        return springUser;
    }

    private Collection<GrantedAuthority> getAuthorities(User u) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Iterator<Role> roles = u.getGrantedRoles().iterator();
        while (roles.hasNext()) {
            Role role = roles.next();
            Iterator<Privilege> pri = role.getPrivileges().iterator();
            while (pri.hasNext()) {
                Privilege pi = pri.next();
                GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + pi.getId());
                authorities.add(auth);
            }
        }
        return authorities;
    }

    public List<Privilege> getUserGrantedMenus(long userid) {
        Set<Privilege> menus = new HashSet<Privilege>();
        User user = userDao.get(userid);
        Iterator<Role> roles = user.getGrantedRoles().iterator();
        while (roles.hasNext()) {
            Role role = roles.next();
            Iterator<Privilege> pri = role.getPrivileges().iterator();
            while (pri.hasNext()) {
                Privilege pi = pri.next();
                if ("menu".equalsIgnoreCase(pi.getNodeType().trim())) {
                    menus.add(pi);
                }
            }
        }
        return null;
    }

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.save(user);
        return user;
    }

}
