/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.framework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.framework.page.Page;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
public class BaseController {

    protected String basePath;

    protected String view(String path) {
        return basePath + path;
    }

    protected void outJson(HttpServletResponse response, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        if (object instanceof Collection) {
            Collection c = (Collection) object;
            Page page = new Page(0, c.size(), c.size(), c );
            response.getWriter().println(mapper.writeValueAsString(page));
        } else {
            response.getWriter().println(mapper.writeValueAsString(object));
        }
    }
}
