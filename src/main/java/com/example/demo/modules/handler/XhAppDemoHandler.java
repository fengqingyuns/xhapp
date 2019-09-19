package com.example.demo.modules.handler;

import org.springframework.stereotype.Component;

@Component
public class XhAppDemoHandler extends XhAppTypeHandler{
    public static final String HANDLER_KEY = "demo";
    @Override
    public String getXhAppType() {
        // TODO Auto-generated method stub
        return HANDLER_KEY;
    }

    @Override
    public String demo() {
        // TODO Auto-generated method stub
        return "demo";
    }

}
