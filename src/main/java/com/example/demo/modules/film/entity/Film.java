package com.example.demo.modules.film.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.baomidou.mybatisplus.annotation.TableName;
@TableName(value = "t_film")
//index类似于数据库，使用type允许我们在一个index中存储多种类型的数据,indexStoreType文件存储类型：fs:默认实现，取决于操作系统
//replicas副本数量默认为1,refreshInterval:多久执行一次刷新操作，使得最近的索引更改对搜索可见。默认是1秒。设置为-1表示禁止刷新。
@Document(indexName="film",type="film",indexStoreType = "fs",shards = 5,replicas=1,refreshInterval="-1")
public class Film implements Serializable{
    @Id
    private String id;
    private int createUserId;
    private String createUserName;
    private String name;
    private String actor;
    private String picture;
    private String flbh1;
    private String flbh2;
    private String serialNo;
    private Date createTime;
    private int hot;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
   
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public int getHot() {
        return hot;
    }
    public void setHot(int hot) {
        this.hot = hot;
    }
   
    public String getSerialNo() {
        return serialNo;
    }
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    public String getFlbh1() {
        return flbh1;
    }
    public void setFlbh1(String flbh1) {
        this.flbh1 = flbh1;
    }
    public String getFlbh2() {
        return flbh2;
    }
    public void setFlbh2(String flbh2) {
        this.flbh2 = flbh2;
    }
    public int getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }
    public String getCreateUserName() {
        return createUserName;
    }
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    @Override
    public String toString() {
        return "Film [id=" + id + ", createUserId=" + createUserId + ", createUserName=" + createUserName + ", name="
                + name + ", actor=" + actor + ", picture=" + picture + ", flbh1=" + flbh1 + ", flbh2=" + flbh2
                + ", serialNo=" + serialNo + ", createTime=" + createTime + ", hot=" + hot + "]";
    }
 
    
}
