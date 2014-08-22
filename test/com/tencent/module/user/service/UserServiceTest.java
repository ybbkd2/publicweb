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
 * @author lenovo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-resource.xml","classpath:appContext-security.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests  {
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        User user = new User();
        user.setUsername("aukoko");
        user.setPassword("123456");
        UserService instance = new UserService();
        instance.createUser(user);
        // TODO review the generated test code and remove the default call to fail.
       
    }

 

    /**
     * Test of list method, of class UserService.
     */
    @Test
    public void testList() {
        System.out.println("list");
        UserService instance = new UserService();
        List<User> expResult = null;
        List<User> result = instance.list();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDao method, of class UserService.
     */
    @Test
    public void testGetUserDao() {
        System.out.println("getUserDao");
        UserService instance = new UserService();
 
        UserDao result = instance.getUserDao();
 
        assertNotNull(result);
 
    }


    
}
