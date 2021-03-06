package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
/*@MapperScan({"com.example.demo.modules.login.dao",
			"com.example.demo.modules.reg.dao",
			"com.example.demo.modules.xiaohua.dao",
			"com.example.demo.modules.job.dao",
			"com.example.demo.modules.shiro.dao",
			"com.example.demo.modules.task.dao",
			"com.example.demo.modules.menu.dao",
			"com.example.demo.modules.user.dao",
			"com.example.demo.modules.miaosha.dao"
	})*/
@MapperScan("com.example.demo.modules.**.dao")
public class XhAppApplication extends SpringBootServletInitializer{
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	     System.setProperty("es.set.netty.runtime.available.processors", "false");
	              return application.sources(XhAppApplication.class);
	          }
	public static void main(String[] args) {
	    System.setProperty("es.set.netty.runtime.available.processors", "false");
        /*SpringApplication springApplication = new SpringApplication(FeixiangApplication.class);
        springApplication.addListeners(new ApplicationStart());
        springApplication.run(args);*/
		SpringApplication.run(XhAppApplication.class, args);
	}
	
}

