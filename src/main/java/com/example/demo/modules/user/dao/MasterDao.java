package com.example.demo.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.modules.user.entity.Master;

@Mapper
public interface MasterDao {
    Master selectOne(Master master);
    List<Master> select();
    void save(Master master);
    void delete(Master master);
    void update(Master master);
}
