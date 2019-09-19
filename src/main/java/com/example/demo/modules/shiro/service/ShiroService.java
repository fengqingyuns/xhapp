package com.example.demo.modules.shiro.service;

import java.util.Set;

import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.entity.UserToken;

public interface ShiroService {
    Set<String> getUserPermissions(Integer userId);
    
    UserToken queryByToken(String accessToken);
    
    User queryUser(Integer userId);
}
