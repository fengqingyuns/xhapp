package com.example.demo.modules.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.user.entity.Group;
import com.example.demo.modules.user.entity.GroupRole;
import com.example.demo.modules.user.service.GroupRoleService;
import com.example.demo.modules.user.service.GroupService;
/*@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private  GroupRoleService groupRoleService; 
    @Override
    public void save(Group group) {

        groupDao.save(group);
        if(group.getRoleList() != null) {
            groupRoleService.save(group.getGroupId(),group.getRoleList());
        }
    }

}
*/