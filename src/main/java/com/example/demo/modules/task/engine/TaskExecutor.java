/**
 * 
 */
package com.example.demo.modules.task.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.demo.modules.task.consts.TaskQueueConsts;


/**
 * 任务执行器
 * @author li.t
 * @date 2018年11月15日 下午4:58:21
 */
public class TaskExecutor {
    private static final ExecutorService executor = 
            Executors.newFixedThreadPool(TaskQueueConsts.TASK_ENGINE_THREAD_POOL_SIZE);
    
    /**
     * 执行任务
     * @param worker
     * @return
     */
    public static boolean execute(TaskWorker worker){
        
        executor.submit(worker);
        
        return true;
    }
}
