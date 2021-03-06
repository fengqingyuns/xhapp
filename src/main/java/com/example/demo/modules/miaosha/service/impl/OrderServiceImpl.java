package com.example.demo.modules.miaosha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.common.Key;
import com.example.demo.modules.miaosha.dao.MiaoKillOrderDao;
import com.example.demo.modules.miaosha.entity.MiaoKillOrder;
import com.example.demo.modules.miaosha.service.OrderService;
import com.example.demo.util.BasicRedis;

@Service
public class OrderServiceImpl extends ServiceImpl<MiaoKillOrderDao,MiaoKillOrder> implements OrderService{

    @Autowired
    private BasicRedis basicRedis;

    @Override
    public MiaoKillOrder getMiaoshaOrderByUserIdGoodsId(Integer userId, Long goodsId) {
        // TODO Auto-generated method stub
        return basicRedis.get(Key.OrderKey+userId, ""+goodsId,MiaoKillOrder.class);
      
    }
    
}
