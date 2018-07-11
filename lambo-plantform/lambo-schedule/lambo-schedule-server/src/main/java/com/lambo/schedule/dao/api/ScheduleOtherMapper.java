package com.lambo.schedule.dao.api;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScheduleOtherMapper {

    int changeStateByPrimaryKey(Map taskMap);
    List selectByTaskId(Map<String,Object> taskMap);
}