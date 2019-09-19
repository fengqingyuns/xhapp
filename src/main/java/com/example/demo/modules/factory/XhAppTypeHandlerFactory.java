package com.example.demo.modules.factory;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

import com.example.demo.modules.handler.XhAppTypeHandler;

public abstract class XhAppTypeHandlerFactory {
	private static final Map<String,XhAppTypeHandler> HANDLER_MAP = Maps.newHashMap();
	public static final String DEFFAULT_HANDLER_KEY = "default";
	
	/**
	 * 获取处理器
	 */
	public static XhAppTypeHandler getHandler(String xhAppType) {
		XhAppTypeHandler handler = HANDLER_MAP.get(xhAppType);
		if(handler == null) {
			handler = HANDLER_MAP.get(DEFFAULT_HANDLER_KEY);
		}
		return handler;
	}
	
	/**
	 * 注册处理器
	 */
	public static void register(String xhAppType, XhAppTypeHandler xhAppHandler) {
		if(StringUtils.isNotBlank(xhAppType)) {
			HANDLER_MAP.put(xhAppType, xhAppHandler);
		}
	}
}
