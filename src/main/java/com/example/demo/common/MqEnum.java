package com.example.demo.common;

public enum MqEnum {

    /**
     * 交换机
     */
    KILL_EXCHANG("kill_exchang","秒杀"),
    
    /**
     * routing key
     */
    KILL_ROUTING_KEY("kill_routing_key","秒杀routing-key");
    
    
    private String code;
    private String msg;
    private MqEnum(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
