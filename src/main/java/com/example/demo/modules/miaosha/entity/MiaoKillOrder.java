package com.example.demo.modules.miaosha.entity;

import java.util.Date;

public class MiaoKillOrder {

    private Integer goodsId;
    
    private Integer orderId;
    
    private String goodsName;
    
    private Date orderStrartTime;
    
    private Date orderEndTime;
    // 0初始 1支付中，2訂單完成，3訂單取消單
    private  int status;
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public Date getOrderStrartTime() {
        return orderStrartTime;
    }
    public void setOrderStrartTime(Date orderStrartTime) {
        this.orderStrartTime = orderStrartTime;
    }
    public Date getOrderEndTime() {
        return orderEndTime;
    }
    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "MiaoKillOrder [goodsId=" + goodsId + ", orderId=" + orderId + ", goodsName=" + goodsName
                + ", orderStrartTime=" + orderStrartTime + ", orderEndTime=" + orderEndTime + ", status=" + status
                + "]";
    }
    
    
}
