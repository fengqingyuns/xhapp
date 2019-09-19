package com.example.demo.modules.user.entity;

import java.util.List;

public class Group {

    private int groupId;
    private String groupName;
    private int pGroupId;
    private List<Role> roleList;
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public int getpGroupId() {
        return pGroupId;
    }
    public void setpGroupId(int pGroupId) {
        this.pGroupId = pGroupId;
    }
    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    @Override
    public String toString() {
        return "Group [groupId=" + groupId + ", groupName=" + groupName + ", pGroupId=" + pGroupId + ", roleList="
                + roleList + "]";
    }
    
}
