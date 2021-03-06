package com.example.demo.modules.film.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName(value = "t_actor")
public class Actor {
    @TableId(value = "id" ,type = IdType.AUTO)
    private int id;
    //演員名稱
    private String name;
    
    private int createUserId;
    
    private String createUserName;
    
    private String picture;
    //特長
    private String speciality;
    //出道時間
    private Date debutTime;
    //演員描述
    private String des;
    private int hot;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDebutTime() {
        return debutTime;
    }
    public void setDebutTime(Date debutTime) {
        this.debutTime = debutTime;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public int getHot() {
        return hot;
    }
    public void setHot(int hot) {
        this.hot = hot;
    }
    public String getCreateUserName() {
        return createUserName;
    }
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public int getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }
    @Override
    public String toString() {
        return "Actor [id=" + id + ", name=" + name + ", createUserId=" + createUserId + ", createUserName="
                + createUserName + ", picture=" + picture + ", speciality=" + speciality + ", debutTime=" + debutTime
                + ", des=" + des + ", hot=" + hot + "]";
    }
   
   
}
