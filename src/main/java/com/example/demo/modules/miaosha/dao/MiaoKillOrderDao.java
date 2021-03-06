package com.example.demo.modules.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.modules.miaosha.entity.MiaoKillOrder;
import com.example.demo.modules.user.entity.User;

@Mapper
public interface MiaoKillOrderDao extends BaseMapper<MiaoKillOrder>{
    public boolean checkPath(User user,long goodsId,String path );
}
