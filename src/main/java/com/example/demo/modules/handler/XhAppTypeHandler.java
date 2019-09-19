package com.example.demo.modules.handler;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.modules.factory.XhAppTypeHandlerFactory;

public abstract class XhAppTypeHandler {
	 private static Logger LOGGER = LoggerFactory.getLogger(XhAppTypeHandler.class);
	@PostConstruct
	public void init() {
		XhAppTypeHandlerFactory.register(getXhAppType(), this);
		LOGGER.info("handler :{}", this);
	}
	public abstract String getXhAppType();
	
	public abstract String demo();
}
