/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tencent.module.security.dao;

import com.tencent.framework.dao.BaseHibernateDao;
import com.tencent.module.security.entity.Privilege;
import com.tencent.module.security.entity.Role;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public class PrivilegeDao extends BaseHibernateDao<Privilege, Long> {
    
    public List<Privilege> getPrivileges(List<Role> roles) {
        return createCriteria(Restrictions.in("role", roles)).list();
    }
    
}
