package com.example.demo.modules.permission.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.permission.dao.PermissionDao;
import com.example.demo.modules.permission.entity.Permission;
import com.example.demo.modules.permission.service.PermissionService;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService{

}
