package com.example.demo.modules.permission.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.modules.permission.entity.Permission;

@Mapper
public interface PermissionDao extends BaseMapper<Permission>{

}
