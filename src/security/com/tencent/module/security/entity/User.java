/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "tbl_user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue
    private long userid;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private int enabled;
    @Column
    private int locked;
    @Column
    private int accountExpired;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> grantedRoles = new HashSet<Role>();

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set authorities = new HashSet();
        Iterator<Role> roles = this.grantedRoles.iterator();
        while (roles.hasNext()) {
            Role role = roles.next();
            Iterator<Privilege> pri = role.getPrivileges().iterator();
            while(pri.hasNext()) {
               Privilege pi = pri.next();
               GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" +pi.getId());
               authorities.add(auth);
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountExpired !=1 ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return (this.locked != 1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return (this.accountExpired != 1);

    }

    @Override
    public boolean isEnabled() {
        return (this.enabled == 1);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the userid
     */
    public long getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(long userid) {
        this.userid = userid;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the locked
     */
    public int getLocked() {
        return locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(int locked) {
        this.locked = locked;
    }

    /**
     * @return the accountExpired
     */
    public int getAccountExpired() {
        return accountExpired;
    }

    /**
     * @param accountExpired the accountExpired to set
     */
    public void setAccountExpired(int accountExpired) {
        this.accountExpired = accountExpired;
    }

}
