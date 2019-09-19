package com.example.demo.modules;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.security.MD5Util;

public class TestWoW {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
        String format = df.format(new Date());
        System.out.println(format);
        int apiid = 16;
        int  timestamp = 20192826;
        String appname = "amap-yingshang";
        String token = "";
        String key = "12a2887fee54e08b0822c29e38c3a763";
     //   String host = "";
        
        int start_date = 39190001;
        int end_date = 39190022; 
        String wowid = "";
        String lab_name = "uv";
        String type = "visit_flow";
        String time_type = "month";
        String str = "123;qwe";
        byte[] bs = MD5Util.md5(str);
        System.out.println(bs);
        
      String s=  " http://140.205.213.250/abapi/query?";
      
         /*   token=cab490cb6041e097d4143cd79e1cf0ba&
            apiid=16&
            timestamp=1537501107&
            appname=
            start_date=20180901&
            end_date=20180901&
            wowid=B0FFFAB6J2&
            lab_name=uv&
            type=
            time_type=month&
            key=*** ";
*/    }

}
