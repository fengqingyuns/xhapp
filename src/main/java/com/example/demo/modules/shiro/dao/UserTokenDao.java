package com.example.demo.modules.shiro.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.entity.UserToken;
@Mapper
public interface UserTokenDao {

 String createToken(Integer userId);
    
    UserToken selectById(Integer userId);
    
    void insert(UserToken userToken);
    
    void updateById(UserToken userToken);
    
    UserToken queryByToken(String token);
    
    User queryById(Integer userId);
}
