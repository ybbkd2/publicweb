/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.user.service;

import com.tencent.module.security.dao.UserDao;
import com.tencent.module.user.dao.UserInfoDao;
import com.tencent.module.security.entity.User;
import com.tencent.module.user.entity.UserInfo;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserInfoDao userInfoDao;

    public void createUser(User user) {
        userDao.save(user);

    }
//   @PostConstruct
//    public void getUser() {
//        System.out.println("getUser() PostConstruct ... ");
//        this.userDao.uniqueResult("username", "admin");
//    }

    public void updateUserInfo(UserInfo ui) {
        this.userInfoDao.saveOrUpdate(ui);
    }

    public List<User> list() {
        return userDao.list();
    }
    
    public User getUserByName( String username) {
        return userDao.uniqueResult("username", username);
    }


}