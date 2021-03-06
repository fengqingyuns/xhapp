package com.example.demo.modules.miaosha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.common.Key;
import com.example.demo.modules.miaosha.service.KillService;
import com.example.demo.modules.user.entity.User;
import com.example.demo.util.BasicRedis;
@Service
public class KillServiceImpl implements KillService{

    @Autowired
    private BasicRedis basicRedis;
    @Override
    public boolean checkPath(User user, long goodsId, String path) {
        // TODO Auto-generated method stub
        if(path == null) return false;
        //旧地址
        String oldPath = "";
    oldPath = basicRedis.get(Key.OrderKey, ""+user.getUsername()+"_"+goodsId, String.class);
        
        return path.equals(oldPath);
    }

   
}
