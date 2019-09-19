package com.example.demo.modules.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.user.entity.GroupRole;
import com.example.demo.modules.user.entity.Role;
import com.example.demo.modules.user.service.GroupRoleService;

/*@Service
public class GroupRoleServiceImpl implements GroupRoleService{

    @Autowired
    private GroupRoleDao groupRoleDao;
    @Override
    public void save(int groupId, List<Role> roleList) {
        groupRoleDao.deleteByGroupId(groupId);
        List<GroupRole> groupRoleList = new ArrayList<>();
        GroupRole temp = new GroupRole();
        for (Role role : roleList) {
            temp.setGroupId(groupId);
            temp.setRoleId(role.getRoleId());
            groupRoleList.add(temp);
        }
        groupRoleDao.save(groupRoleList);
    }

}
*/