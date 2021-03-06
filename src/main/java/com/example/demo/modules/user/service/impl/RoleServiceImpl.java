package com.example.demo.modules.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.user.dao.RoleDao;
import com.example.demo.modules.user.entity.Role;
import com.example.demo.modules.user.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService{

    @Override
    public List<Role> queryRoleList(int createMasterId) {
        // TODO Auto-generated method stub
        return null;
    }

}
