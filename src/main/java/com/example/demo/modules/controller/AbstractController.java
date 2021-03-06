package com.example.demo.modules.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.modules.user.entity.User;

public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
    protected int getUserId() {
        return getUser().getId();
    }
}
