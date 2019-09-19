package com.example.demo.modules.user.service;

import java.util.List;

import com.example.demo.modules.user.entity.Role;

public interface GroupRoleService {

    void save(int groupId,List<Role> roleList);
}
