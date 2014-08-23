/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.user.controller;

import com.google.gson.Gson;
import com.tencent.framework.controller.BaseController;
import com.tencent.framework.util.MD5Util;
import com.tencent.module.security.entity.User;
import com.tencent.module.user.entity.UserInfo;
import com.tencent.module.user.service.UserService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
@RequestMapping("/super/module/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping("/list.do")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        List<User> list = userService.list();



        User tu = userService.getUserByName("admin");

        if (tu == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(MD5Util.md5("admin"));
            user.setEnabled(1);
            user.setLocked(0);

            userService.createUser(user);
            
            UserInfo ui = new UserInfo();
            ui.setUserid(user.getUserid());
            ui.setCellphone("123456789011");
            
            userService.updateUserInfo(ui);
            
        }
        //new Gson().toJson(list)
        response.getWriter().println("OK");
    }
}
