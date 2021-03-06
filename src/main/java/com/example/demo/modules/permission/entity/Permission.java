package com.example.demo.modules.permission.entity;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName(value = "t_permission")
public class Permission {

	private Integer id;
	private Integer pid;
	private String name;
	private boolean open = true;
	private boolean checked = false;
	private String icon;
	private String url;
	   @TableField(exist = false)
	private List<Permission> children = new ArrayList<Permission>();
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<Permission> getChildren() {
		return children;
	}
	public void setChildren(List<Permission> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", pid=" + pid + ", name=" + name + ", open=" + open + ", checked=" + checked
				+ ", icon=" + icon + ", url=" + url + ", children=" + children + "]";
	}
	
}
