/**
 * 
 */
package com.example.demo.modules.job.handler;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.example.demo.modules.task.engine.TaskEngine;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 执行任务队列作业处理器
 * @author liyinglong@hanyun.com
 * @date 2017年10月25日 上午11:50:41
 */
@Component
public class TaskQueueJobHandler  {
    @Resource
    private TaskEngine taskEngine;

    /**
     * 调试中心可传参数：无
     */
    @XxlJob("taskHandler")
    public ReturnT<String> taskHandler(String param) throws Exception {
        
        int tasknum = taskEngine.run();
        XxlJobLogger.log("tasknum: {0}", tasknum);
        
        return ReturnT.SUCCESS;
    }

}
