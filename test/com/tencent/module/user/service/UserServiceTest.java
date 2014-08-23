/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tencent.module.user.service;

import com.tencent.module.security.dao.UserDao;
import com.tencent.module.security.entity.User;
import com.tencent.module.user.entity.UserInfo;
import java.util.List;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author guoxp
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-resource.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests  {
 
    @Resource
    UserService userService;
    
    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        User user = new User();
        user.setUsername("aukoko");
        user.setPassword("123456");
        userService.createUser(user);
        assertTrue(user.getUserid() > 0);

    }
}
//    /**
//     * Test of updateUserInfo method, of class UserService.
//     */
//    @Test
//    public void testUpdateUserInfo() {
//        System.out.println("updateUserInfo");
//        UserInfo ui = null;
//        UserService instance = new UserService();
//        instance.updateUserInfo(ui);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class UserService.
//     */
//    @Test
//    public void testList() {
//        System.out.println("list");
//        UserService instance = new UserService();
//        List<User> expResult = null;
//        List<User> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserDao method, of class UserService.
//     */
//    @Test
//    public void testGetUserDao() {
//        System.out.println("getUserDao");
//        UserService instance = new UserService();
//        UserDao expResult = null;
//        UserDao result = instance.getUserDao();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUserDao method, of class UserService.
//     */
//    @Test
//    public void testSetUserDao() {
//        System.out.println("setUserDao");
//        UserDao userDao = null;
//        UserService instance = new UserService();
//        instance.setUserDao(userDao);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
 