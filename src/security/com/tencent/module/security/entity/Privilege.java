/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author lenovo 权限资源定义
 */
@Entity
@Table(name = "tbl_privilege")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    @Column
    private String name;
    @Column
    private String matchUrl;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Privilege parent;//上级 

    @OneToMany(mappedBy = "parent", targetEntity = Privilege.class, cascade = CascadeType.ALL)
    private java.util.Set<Privilege> children = new HashSet<Privilege>();//下级 

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_privilege", joinColumns = @JoinColumn(name = "privilege_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private java.util.Set<Role> grantedRoles = new HashSet<Role>();//被授权的ROLE

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the matchUrl
     */
    public String getMatchUrl() {
        return matchUrl;
    }

    /**
     * @param matchUrl the matchUrl to set
     */
    public void setMatchUrl(String matchUrl) {
        this.matchUrl = matchUrl;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the parent
     */
    public Privilege getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Privilege parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    public java.util.Set<Privilege> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(java.util.Set<Privilege> children) {
        this.children = children;
    }

    /**
     * 添加子权限
     */
    public void addChildPrivilege(Privilege pri) {
        if (pri == null) {
            throw new IllegalArgumentException("Can't add a null Privilege as child.");
        }
        // 删除旧的父类 
        if (pri.getParent() != null) {
            pri.getParent().getChildren().remove(pri);
        }
        // 设置新的父类 
        pri.setParent(this);
        // 向当前 对象中加入 
        this.getChildren().add(pri);
    }

    /**
     * @return the grantedRoles
     */
    public java.util.Set<Role> getGrantedRoles() {
        return grantedRoles;
    }

    /**
     * @param grantedRoles the grantedRoles to set
     */
    public void setGrantedRoles(java.util.Set<Role> grantedRoles) {
        this.grantedRoles = grantedRoles;
    }

}
