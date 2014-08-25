/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.framework.controller.BaseController;
import com.tencent.framework.page.Page;
import com.tencent.module.security.entity.User;
import com.tencent.module.user.service.UserService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

        String pageStr =request.getParameter("page");
        int page = Integer.parseInt( pageStr==null|| pageStr.equals("") ? "0" : pageStr );
        
        String limitStr = request.getParameter("limit");
        int pagesize = Integer.parseInt(limitStr==null || limitStr.equals("") ? "0":limitStr);
        
        Page<User> list = userService.pagedlist(page,pagesize);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().println(mapper.writeValueAsString(list));
        
    }
}
