package com.example.demo.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.modules.user.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User>{

    public void delUserRole(Integer id);
    
    public List<Integer> selectRoleIdsById(Integer id);

 //   public void delRoleOfUser(int userId);

    public void assignRole(@Param("ids") Integer[] ids,@Param("userId") int userId);

    public void unAssignRole(@Param("ids") Integer[] ids, @Param("userId") int userId);
}
