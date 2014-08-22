/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.user.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "tbl_sex_type")
public class SexType implements Serializable {
    @Id
    @GeneratedValue
    private int sexTypeId;
    @Column(length = 200)
    private String sexName;

    /**
     * @return the sexTypeId
     */
    public int getSexTypeId() {
        return sexTypeId;
    }

    /**
     * @param sexTypeId the sexTypeId to set
     */
    public void setSexTypeId(int sexTypeId) {
        this.sexTypeId = sexTypeId;
    }

    /**
     * @return the sexName
     */
    public String getSexName() {
        return sexName;
    }

    /**
     * @param sexName the sexName to set
     */
    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
    
}
