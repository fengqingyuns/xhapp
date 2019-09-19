package com.example.demo.modules.user.service;

import java.util.List;

import com.example.demo.modules.user.entity.MasterRole;
import com.example.demo.modules.user.entity.Role;

public interface MasterRoleService {

    void saveOrUpdate(int masterId,List<Role> roleList);
}
