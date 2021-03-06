package com.example.demo.modules.entity;

import java.util.Date;

public class CommonReturnEntity {

    private int no;
    
    private String name;
    //主演
    private String actName;
    
    private Date createTime;
    
    private String picture;
    //磁力
    private String force;
    
    private String phone;
    //职务
    private String job;
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getActName() {
        return actName;
    }
    public void setActName(String actName) {
        this.actName = actName;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getForce() {
        return force;
    }
    public void setForce(String force) {
        this.force = force;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    @Override
    public String toString() {
        return "CommonReturnEntity [no=" + no + ", name=" + name + ", actName=" + actName + ", createTime=" + createTime
                + ", picture=" + picture + ", force=" + force + ", phone=" + phone + ", job=" + job + "]";
    }
    
    
}
