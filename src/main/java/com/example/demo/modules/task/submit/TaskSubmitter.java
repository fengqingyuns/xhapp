/**
 * 
 */
package com.example.demo.modules.task.submit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.modules.task.consts.BizResCode;
import com.example.demo.modules.task.consts.TaskQueueConsts;
import com.example.demo.modules.task.domin.TaskQueue;
import com.example.demo.modules.task.exception.BizException;
import com.example.demo.modules.task.service.TaskQueueService;
import com.example.demo.modules.task.util.BusinessIdUtils;
import com.example.demo.modules.task.util.JsonUtil;


/**
 * 任务提交器
 * @author liyinglong@hanyun.com
 * @date 2016年11月15日 下午5:39:51
 */
@Service
public class TaskSubmitter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSubmitter.class);
    @Resource
    private TaskQueueService taskQueueService;
    
    public boolean submit(TaskQueue task){
        try {
            LOGGER.info("task subimt: {}", JsonUtil.toJson(task));
            
            task.setMessageId(BusinessIdUtils.genTaskMessageId());
            task.setStatus(TaskQueueConsts.OPERATE_STATUS_UNDEAL);
            task.setRetryCount(0);
            
            taskQueueService.addSelective(task);
        } catch (Exception e) {
            LOGGER.error("task submit error", e);
            throw BizException.build(BizResCode.SYSTEMERROR);
        }
        return true;
    }
}
