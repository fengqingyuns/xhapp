package com.example.demo.modules.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.modules.user.entity.Role;
@Mapper
public interface RoleDao extends BaseMapper<Role>{

}
