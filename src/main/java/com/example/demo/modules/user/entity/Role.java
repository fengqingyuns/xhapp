package com.example.demo.modules.user.entity;

import java.util.Date;
import java.util.List;
public class Role {

    private int roleId;
    private String roleName;
    private String remark;
    private int createUserId;
    private List<Long> menuIdList;
    private Date createTime;
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }
    public List<Long> getMenuIdList() {
        return menuIdList;
    }
    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + ", remark=" + remark + ", createUserId="
                + createUserId + ", menuIdList=" + menuIdList + ", createTime=" + createTime + "]";
    }

  
}
