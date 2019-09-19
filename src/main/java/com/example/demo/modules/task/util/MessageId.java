package com.example.demo.modules.task.util;

/**
 * <pre>
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * </pre>
 * <p>
 * Date: 16/8/10
 * Time: 20:32
 *
 * @author heroin.nee@gmail.com
 */
public class MessageId {

    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MESSAGE = "SUCCESS";

    private String code;
    private String message;

    protected MessageId(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static MessageId create(final Project project, final int code, final String message) {
        return new MessageId(String.format("%s%04d", project.getNo(), code), message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
