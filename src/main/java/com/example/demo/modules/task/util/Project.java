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
 * Time: 20:40
 *
 * @author heroin.nee@gmail.com
 */
@SuppressWarnings("WeakerAccess")
public enum Project {

    ZERO(0, "zero"),
    BRAND_UI(1, "website"),
    BRAND_API(2, "brand"),
    MEMBER_API(3, "member"),
    MARKET_API(4, "market"),
    PAYMENT_API(5, "payment"),
    SETTLEMENT_API(6, "settlement"),
    TRANSACTION_API(7, "transaction"),
    WECHAT_API(8, "wx"),
    SCM_API(9, "scm"),
    PRODUCT_API(10,"product"),
    FINANCE_API(11,"finance"),
    MEMBER_POINTS_API(12, "points");
    private String no;
    private String name;

    private Project(int no, String name) {
        this.no = String.format("%03d", no);
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }
}
