package com.example.demo.modules.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.modules.miaosha.entity.MiaoKillOrder;

public interface OrderService extends IService<MiaoKillOrder>{

    MiaoKillOrder getMiaoshaOrderByUserIdGoodsId(Integer userId,Long goodsId);
}
