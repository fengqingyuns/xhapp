package com.example.demo.modules.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.modules.user.entity.Role;

public interface RoleService extends IService<Role>{

    List<Role> queryRoleList(int createMasterId);
   
}
