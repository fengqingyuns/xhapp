package com.example.demo.modules.vue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modules.factory.XhAppTypeHandlerFactory;
import com.example.demo.modules.handler.XhAppTypeHandler;

@Controller
public class VueController {

	@RequestMapping("/toVue")
	public String gotoVue() {
		return "vue/vue";
	}
	@RequestMapping("/demo")
	public void demo() {
	    String xhAppType = "default";
	    XhAppTypeHandler handler = XhAppTypeHandlerFactory.getHandler(xhAppType);
	    handler.demo();
	}
}
