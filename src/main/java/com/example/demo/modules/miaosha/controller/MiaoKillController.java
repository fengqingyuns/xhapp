package com.example.demo.modules.miaosha.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.example.demo.modules.common.Key;
import com.example.demo.modules.miaosha.entity.MiaoKillOrder;
import com.example.demo.modules.miaosha.service.KillService;
import com.example.demo.modules.miaosha.service.OrderService;
import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.util.R;
import com.example.demo.util.BasicRedis;

import static com.example.demo.common.ResultStatus.*;
import static com.example.demo.common.MqEnum.*;
import java.util.HashMap;
@Controller
@RequestMapping("/miaokill")
public class MiaoKillController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private KillService killService;
    @Autowired
    private BasicRedis basicRedis;
    
    @Autowired
    private AmqpTemplate rabbitTemplate;
    
    private HashMap<Long,Boolean> localOverMap = new HashMap<Long,Boolean>();
    
    @RequestMapping(value ="/{path}/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public R miaoKill( @PathVariable("path") String path,User user, Long goodsId) {
        
        HashMap<String,Long> mqMap = new HashMap<String,Long>();
        if(user == null) {
            return R.error(USER_NOT_EXIST.getCode(),USER_NOT_EXIST.getMsg());
        }
        //访问频繁限制
       /* boolean checkPath = killService.checkPath(user, goodsId, path);
        if(!checkPath) {
            R.error(REQUEST_ILLEGAL.getCode(),REQUEST_ILLEGAL.getMsg() );
        }*/
        //已经秒杀到
        MiaoKillOrder miaoKillOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(miaoKillOrder != null) {
            return R.error(EXCEPTION.getCode(),REPEATE_MIAOSHA.getMsg());
        }
        //内存标记减少redis访问
        boolean over = localOverMap.get(goodsId);
        if(over) {
            return R.error(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMsg());
        }
        //遇见库存
        long incr = basicRedis.incr(""+Key.GoodsKey+"_"+goodsId, -1l);
        if(incr <0) {
            localOverMap.put(goodsId, true);
            return R.error(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMsg());
        }
        //发送消息
        mqMap.put("goodsId", goodsId);
        String message = JSON.toJSONString(mqMap);
        rabbitTemplate.convertAndSend(KILL_EXCHANG.getCode(),KILL_ROUTING_KEY.getCode(),message);
        return R.ok();
    }
}
