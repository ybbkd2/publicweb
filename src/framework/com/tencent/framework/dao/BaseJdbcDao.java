/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.framework.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class BaseJdbcDao extends JdbcDaoSupport {

    @Resource
    protected DataSource dataSource;

    @PostConstruct
    void init() {
        setDataSource(dataSource);
    }
}
