package com.example.demo.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.modules.user.entity.MasterGroup;

@Mapper
public interface MasterGroupDao {

    void save(@Param("masterGroupList") List<MasterGroup> masterGroupList);
    void deleteByMasterId(int masterId);
}
