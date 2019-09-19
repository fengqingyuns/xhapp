package com.example.demo.modules.user.entity;

public class GroupRole {

    private int groupId;
    private int roleId;
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return "RoleGroup [groupId=" + groupId + ", roleId=" + roleId + "]";
    }
    
}
