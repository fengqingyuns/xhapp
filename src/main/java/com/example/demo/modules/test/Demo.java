package com.example.demo.modules.test;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.demo.config.KeyConfig;
import com.example.demo.http.HttpClient;
import com.example.demo.http.HttpClientResponse;

/**
 * Date: 2019/9/12 13:56
 * author: litao
 */
public class Demo {
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 20000;
    public static void main(String[] args) {
        String url = "https://wq.jd.com/commodity/comment/getfoldcommentlist?callback=testHaveFoldEvalB&sorttype=5&pagesize=10&sceneval=2&rid=97faa54455fa18a6&sku=2747624&page=1&t=0.9263222787981278";
       
        HttpClientResponse response = doRequest(url,null);
        System.out.println(response.getBody());
    }
    
    
    protected static HttpClientResponse doRequest(String url, NameValuePair[] data) {
        HttpClientResponse hcres = null;
        try {
            HttpClient httpClient = HttpClient.get(url);
            httpClient.timeout(HTTP_CONN_TIME_OUT, HTTP_SOCKET_TIME_OUT);
            httpClient.setHeader("authority", "wq.jd.com");
            httpClient.setHeader("path", "/commodity/comment/getfoldcommentlist?callback=testHaveFoldEvalB&sorttype=5&pagesize=10&sceneval=2&rid=97faa54455fa18a6&sku=2747624&page=1&t=0.9263222787981278");
            httpClient.setHeader("scheme", "https");
            httpClient.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpClient.setHeader("referer", "https://item.m.jd.com/product/2747624.html");
            httpClient.setHeader("sec-fetch-mode", "no-cors");
            httpClient.setHeader("sec-fetch-site", "same-site");
            
            hcres = httpClient.action();
        } catch (Exception e) {
      
        }
        return hcres;
    }
}
