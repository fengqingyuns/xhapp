package com.example.demo.modules.user.service;

import java.util.List;

import com.example.demo.modules.user.entity.Role;

public interface RoleService {

    List<Role> queryRoleList(int createMasterId);
   
}
