package com.example.demo.modules.bootmq;

import java.io.Serializable;

public class TestUser implements Serializable{

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TestUser [userName=" + userName + "]";
    }
    
    
}
