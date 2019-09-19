package com.example.demo.modules.user.entity;

public class MasterRole {

    private int id;
    private int masterId;
    private int roleId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMasterId() {
        return masterId;
    }
    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return "MasterRole [id=" + id + ", masterId=" + masterId + ", roleId=" + roleId + "]";
    }
    
}
