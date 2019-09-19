/**
 * 
 */
package com.example.demo.modules.task.exception;

import com.example.demo.modules.task.util.MessageId;

/**
 * 业务异常
 * @author liyinglong@hanyun.com
 * @date 2016年7月31日 下午5:14:49
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 4863980120893135018L;
    
    /** 错误码 */
    private MessageId errorMsg;
        
    public BizException(MessageId errorMsg){
        super(errorMsg.getCode() + "-" + errorMsg.getMessage());
        this.errorMsg = errorMsg;
    }
    
    public static BizException build(MessageId errorMsg){
        return new BizException(errorMsg);
    }
    
    public MessageId getErrorMsg(){
        return errorMsg;
    }
    
    public String getErrorCode(){
        return errorMsg.getCode();
    }
    
    public String getErrorDesc(){
        return errorMsg.getMessage();
    }
}
