package com.example.demo.modules.handler;

import org.springframework.stereotype.Component;

import com.example.demo.modules.factory.XhAppTypeHandlerFactory;
@Component
public class XhAppDefaultHandler extends XhAppTypeHandler{

	public String getXhAppType() {
		// TODO Auto-generated method stub
		return XhAppTypeHandlerFactory.DEFFAULT_HANDLER_KEY;
	}

    @Override
    public String demo() {
        // TODO Auto-generated method stub
        return "default";
    }

}
