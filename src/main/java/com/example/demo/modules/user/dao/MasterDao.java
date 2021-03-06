package com.example.demo.modules.user.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MasterDao {
   // Master selectOne(Master master);
   // List<Master> select();
   // void save(Master master);
  //  void delete(Master master);
  //  void update(Master master);
    List<String> queryAllPerms(int userId);
    
    List<Integer> queryMenuIdsByUserId(int userId);
}
