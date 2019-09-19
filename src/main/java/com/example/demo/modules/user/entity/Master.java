package com.example.demo.modules.user.entity;

import java.util.List;

public class Master {
    private int masterId;
    private String name;
    private String psssword;
    private String email;
    private String phone;
    private String address;
    private String sex;
    private String birthday;
    private int createMasterId;
    private String createDate;
    private List<Role> roleList;
    private List<Group> groupList;
    
    public int getCreateMasterId() {
        return createMasterId;
    }
    public void setCreateMasterId(int createMasterId) {
        this.createMasterId = createMasterId;
    }
    public int getMasterId() {
        return masterId;
    }
    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public String getPsssword() {
        return psssword;
    }
    public void setPsssword(String psssword) {
        this.psssword = psssword;
    }
    
    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    public List<Group> getGroupList() {
        return groupList;
    }
    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
    @Override
    public String toString() {
        return "Master [masterId=" + masterId + ", name=" + name + ", psssword=" + psssword + ", email=" + email
                + ", phone=" + phone + ", address=" + address + ", sex=" + sex + ", birthday=" + birthday
                + ", createMasterId=" + createMasterId + ", createDate=" + createDate + ", roleList=" + roleList
                + ", groupList=" + groupList + "]";
    }
   
}
