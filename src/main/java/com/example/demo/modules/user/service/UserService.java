package com.example.demo.modules.user.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.modules.user.entity.User;

public interface UserService extends IService<User>{
    //根据用户id查询角色
    List<Integer> roleIds(Integer id);

    void delRoleOfUser(int userId);

    boolean assignRole(Integer[] ids,int puserid, int userId);

    void delUserRole(int userId);

    boolean unAssignRole(Integer[] ids, int puserid,int userId);

}
