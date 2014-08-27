/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
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

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = -4400386683517864019L;

    @Id
    @GeneratedValue

    private long userid;
    
    @Column(unique = true)
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> grantedRoles = new HashSet<Role>();



    /**
     * @return the grantedRoles
     */
    public Set<Role> getGrantedRoles() {
        return grantedRoles;
    }

    /**
     * @param grantedRoles the grantedRoles to set
     */
    public void setGrantedRoles(Set<Role> grantedRoles) {
        this.grantedRoles = grantedRoles;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
