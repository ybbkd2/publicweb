/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.module.security.controller;

import com.tencent.framework.controller.BaseController;
import com.tencent.module.security.entity.Privilege;
import com.tencent.module.security.service.PrivilegeService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author guoxp
 */
@Controller
@RequestMapping("/super/security")
public class ResourceController extends BaseController {

    @Autowired
    PrivilegeService privilegeService;

    @RequestMapping("/menu.do")
    public void getMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        Map<Long, Privilege> prs = this.privilegeService.getMyPrivileges();
        
        this.outJson(response, prs.values() );
    }

}
