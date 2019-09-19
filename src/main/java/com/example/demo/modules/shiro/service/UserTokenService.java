package com.example.demo.modules.shiro.service;

import com.example.demo.modules.user.entity.UserToken;

public interface UserTokenService {

    String createToken(Integer userId);
    
    UserToken selectById(Integer userId);
    
    void insert(UserToken userToken);
    
    void updateById(UserToken userToken);
}
