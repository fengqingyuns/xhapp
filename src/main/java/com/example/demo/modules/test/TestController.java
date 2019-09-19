package com.example.demo.modules.test;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.task.consts.TaskMessageType;
import com.example.demo.modules.task.domin.TaskQueue;
import com.example.demo.modules.task.submit.TaskSubmitter;


@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TaskSubmitter taskSubmitter;
    @RequestMapping("/task")
    public String testTask() {
        TaskQueue task = new TaskQueue();
        task.setMessageType(TaskMessageType.ALIPAY_MSC_QUERY_ORDER);
        task.setMessage("123");
        task.setPayId("456");
        task.setTransId("789");
        taskSubmitter.submit(task);
        
        return "success";
    }
}
