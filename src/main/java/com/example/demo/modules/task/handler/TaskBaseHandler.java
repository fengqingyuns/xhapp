package com.example.demo.modules.task.handler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.example.demo.modules.task.domin.TaskQueue;
import com.example.demo.modules.task.service.TaskQueueService;


/**
 * Created by wangzhen on 2016-08-05.
 */
public abstract class TaskBaseHandler {
    @Resource
    private TaskQueueService taskQueueService;

    @PostConstruct
    public void init() {
        TaskHandlerManage.register(getMessageType(), this);
    }

    public abstract Integer getMessageType();

    public abstract HandleResult handle(TaskQueue task);
}
