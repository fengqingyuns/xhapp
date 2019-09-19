package com.example.demo.modules.zgdream;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.demo.http.HttpClient;
import com.example.demo.http.HttpClientResponse;

public class DuanXin {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        NameValuePair[] data = {
                new BasicNameValuePair("PhoneNumberJson", "13261127176"),
                new BasicNameValuePair("SignNameJson", "赢商数智科技有限公司"),
                new BasicNameValuePair("TemplateCode", "SMS_160980049")};
        try {
            HttpClientResponse response = doRequest("dysmsapi.aliyuncs.com", data);
          
            String body = response.getBody();
           
        } catch (Exception e) {
          
        }
    }
    protected static HttpClientResponse doRequest(String url, NameValuePair[] data) {
        HttpClientResponse hcres = null;
        try {

     
            //http client time out conn socket time out url socket time
            HttpClient httpClient = HttpClient.post(url);
         

            httpClient.addParameters(data);
            hcres = httpClient.action();
        } catch (Exception e) {
      

        }
        return hcres;
    }

}
