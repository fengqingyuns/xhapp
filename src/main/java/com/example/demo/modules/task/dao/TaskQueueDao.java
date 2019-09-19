package com.example.demo.modules.task.dao;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.modules.task.domin.TaskQueue;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface TaskQueueDao {

    public int deleteByMessageId(String messageId);

    public int insertSelective(TaskQueue record);

    public TaskQueue selectByMessageId(String messageId);

    public List<TaskQueue> selectUndisposedTaskQueue(Integer rows);

    public List<TaskQueue> selectDisposedTaskQueue(Date limitTime);

    public int updateByMessageIdSelective(TaskQueue record);

    public int updateDisposedTaskQueue(Date limitDate);

    public int batchUpdateByMessageIdSelective(List<TaskQueue> list);
}