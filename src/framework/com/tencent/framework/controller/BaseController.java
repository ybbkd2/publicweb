/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tencent.framework.controller;

/**
 *
 * @author lenovo
 */
public class BaseController {
    protected String basePath;
    
    protected String view(String path) {
        return basePath + path;
    }
}
