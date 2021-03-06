package com.example.demo.modules.util;

import java.util.HashMap;


public class AJAXResult {

	private boolean success;
	private String msg;
	private Object data;
	private Integer id;
	private String key;
	private String userName;
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "AJAXResult [success=" + success + ", msg=" + msg + ", data=" + data + ", id=" + id + ", key=" + key
                + ", userName=" + userName + "]";
    }
	
}
