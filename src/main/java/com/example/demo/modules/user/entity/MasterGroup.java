package com.example.demo.modules.user.entity;

public class MasterGroup {

    private int groupId;
    private int masterId;
  
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getMasterId() {
        return masterId;
    }
    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
    @Override
    public String toString() {
        return "MasterGroup [groupId=" + groupId + ", masterId=" + masterId + "]";
    }
   
    
}
