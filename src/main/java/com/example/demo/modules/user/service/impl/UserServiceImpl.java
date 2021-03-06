package com.example.demo.modules.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.user.dao.UserDao;
import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User>  implements UserService {

    
    @Autowired
    private UserDao userDao;
    @Override
    public List<Integer> roleIds(Integer id) {
        // TODO Auto-generated method stub
        List<Integer> list = userDao.selectRoleIdsById(id);
        return list;
    }
   
   
    @Override
    public void delUserRole(int userId) {
        userDao.delUserRole(userId);
    }

    @Override
    public void delRoleOfUser(int userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean unAssignRole(Integer[] ids,int puserid, int userId) {
        boolean flag = true;
        if(puserid != userId && userId == 1) {
            flag = false;
            return flag;
        }
        userDao.unAssignRole(ids,userId);
        return flag;
    }

    @Override
    public boolean assignRole(Integer[] ids,int puserid, int userId) {
        boolean flag = true;
        if(puserid != userId && userId == 1) {
            flag = false;
            return flag;
        }
        userDao.assignRole(ids,userId);
        return flag;
    }



}
