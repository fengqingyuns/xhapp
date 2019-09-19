package com.example.demo.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.modules.user.entity.MasterRole;

@Mapper
public interface MasterRoleDao {

    void saveOrUpdate(@Param("masterRoleList") List<MasterRole> masterRoleList);
    void deleteByMasterId(int masterId); 
}
