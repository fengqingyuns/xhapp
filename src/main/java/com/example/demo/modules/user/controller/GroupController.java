package com.example.demo.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modules.user.entity.Group;
import com.example.demo.modules.user.service.GroupService;

/*@Controller
@RequestMapping("/group")*/
public class GroupController {

   /* @Autowired
    private GroupService groupService;
    @RequestMapping("/save")*/
    public void save(Group group) {
    //    groupService.save(group);
    }
}
