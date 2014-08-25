/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.user.controller;

import com.google.gson.Gson;
import com.tencent.framework.controller.BaseController;
import com.tencent.framework.page.Page;
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

        int page = Integer.parseInt(request.getParameter("page"));
        int pagesize = Integer.parseInt(request.getParameter("limit"));
        
        Page<User> list = userService.pagedlist(page,pagesize);
        response.getWriter().println(new Gson().toJson(list));
        
    }
}
