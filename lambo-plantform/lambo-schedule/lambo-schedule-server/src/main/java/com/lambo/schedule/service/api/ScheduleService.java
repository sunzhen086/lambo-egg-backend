package com.lambo.schedule.service.api;


import com.lambo.common.base.BaseService;
import com.lambo.schedule.model.ScheduleTask;
import com.lambo.schedule.model.ScheduleTaskExample;

import java.util.List;
import java.util.Map;

/**
* CategoryService接口
* Created by zxc on 2018/2/28.
*/
public interface ScheduleService extends BaseService<ScheduleTask, ScheduleTaskExample> {
    public int changeStateByPrimaryKey(Map taskMap);
    public List selectByTaskId(Map<String,Object> taskMap);
}