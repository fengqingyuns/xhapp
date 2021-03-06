package com.example.demo.common;
/**
* 普通返回类
* 1打头 系统系列错误
* 2 注册登录系列错误
* 3 check 系列错误
* 4 秒杀错误
* 5 商品错误
* 6 订单错误
* 7 用户错误
* @author qiurunze
*/
public enum ResultStatus {
    SUCCESS(1,"成功"),
    FAILD(-1,"失败"),
    EXCEPTION(-1,"系统异常"),
    PARAM_ERROR(10000,"参数错误"),
    SYSTEM_ERROR(10001,"系统错误"),
    FILE_NOT_EXIST(10002,"文件不存在"),
    SYSTEM_DB_ERROR(10006,"数据库系统错误"),
    DATA_ALREADY_PEXISTS(10008,"数据已经存在"),
    
    /** 
     * 注册
     */
    RESIGETER_SUCCESS(2000,"注册成功！"),
    RESIGETER_FAIL(20001,"注册失败！"),
    CODE_FAIL(20002,"验证码不一致"),
    /**
     * 管理员
     */
    NOT_CREATE_USER(0001,"只有创建者能删除此用户"),
    /**
     * check
     */
    BIND_ERROR (30001,"参数校验异常：%s"),
    ACCESS_LIMIT_REACHED (30002,"请求非法!"),
    REQUEST_ILLEGAL (30004,"访问太频繁!"),
    SESSION_ERROR (30005,"Session不存在或者已经失效!"),
    PASSWORD_EMPTY (30006,"登录密码不能为空!"),
    MOBILE_EMPTY (30007,"手机号不能为空!"),
    MOBILE_ERROR (30008,"手机号格式错误!"),
    MOBILE_NOT_EXIST (30009,"账号不存在!"),
    PASSWORD_ERROR (30010,"密码错误!"),
    USER_NOT_EXIST(30011,"用户不存在！"),
    
    /**
     * 订单模块
     */
    ORDER_NOT_EXIST(60001,"订单不存在"),
    
    
    /**
     * 秒杀模块
     */
    MIAO_SHA_OVER(40001,"商品已经秒杀完毕"),
    REPEATE_MIAOSHA(40002,"不能重复秒杀"),
    MIAOSHA_FAIL(40003,"秒杀失败"),
    /**
     * 電影
     * **/
    FILM_INFOS_NOT_EMPTY(60001,"請先刪除電影信息"),
    
    /**
     * 权限
     */
    PERMISSION_SUCCESS(50001,"修改成功"),
    PERMISSION_ERROR(50002,"越权");
    private int code;
    
    private String msg;
    
    private ResultStatus(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    private ResultStatus(Object... args) {
        this.msg = String.format(this.msg, args);
    }
   
    
}
