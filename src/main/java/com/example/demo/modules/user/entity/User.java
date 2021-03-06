package com.example.demo.modules.user.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_user")
public class User {
    @TableId(value = "id" ,type = IdType.AUTO)
	private Integer id;
	private String loginacct;
	private String userpswd;
	private String username;
	private String email;
	private Date createtime;
	private Integer createUserId;
	private String type;
	private String phone;
	private Integer status;
	@TableField(exist = false)
	private String token;
	
	@TableField(exist = false)
	private List<Role> roleList;
	
	public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginacct() {
		return loginacct;
	}
	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}
	public String getUserpswd() {
		return userpswd;
	}
	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    public Integer getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", loginacct=" + loginacct + ", userpswd=" + userpswd + ", username=" + username
                + ", email=" + email + ", createtime=" + createtime + ", createUserId=" + createUserId + ", type="
                + type + ", phone=" + phone + ", status=" + status + ", token=" + token + ", roleList=" + roleList
                + "]";
    }
   

	
}
