package com.example.demo.modules.task.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.modules.task.domin.TaskQueueHis;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface TaskQueueHisDao {

    public int deleteByMessageId(String id);

    public int insertSelective(TaskQueueHis record);

    public TaskQueueHis selectByMessageId(String id);

    public List<TaskQueueHis> selectSelective(TaskQueueHis record);

    public int updateByPrimaryKeySelective(TaskQueueHis record);
}