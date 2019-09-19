package com.example.demo.modules.shiro.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modules.shiro.dao.UserTokenDao;
import com.example.demo.modules.shiro.service.ShiroService;
import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.entity.UserToken;
@Service
public class ShiroServiceImpl implements ShiroService{

    @Autowired
    private UserTokenDao UserTokenDao;
    @Override
    public Set<String> getUserPermissions(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserToken queryByToken(String accessToken) {
        // TODO Auto-generated method stub
        UserToken userToken = UserTokenDao.queryByToken(accessToken);
        return userToken;
    }

    @Override
    public User queryUser(Integer userId) {
        // TODO Auto-generated method stub
        User user = UserTokenDao.queryById(userId);
        return user;
    }

}
