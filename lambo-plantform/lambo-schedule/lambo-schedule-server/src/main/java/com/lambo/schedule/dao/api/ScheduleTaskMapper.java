package com.lambo.schedule.dao.api;

import com.lambo.schedule.model.ScheduleTask;
import com.lambo.schedule.model.ScheduleTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleTaskMapper {
    int deleteByExample(ScheduleTaskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(ScheduleTask record);

    int insertSelective(ScheduleTask record);

    List<ScheduleTask> selectByExample(ScheduleTaskExample example);

    ScheduleTask selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") ScheduleTask record, @Param("example") ScheduleTaskExample example);

    int updateByExample(@Param("record") ScheduleTask record, @Param("example") ScheduleTaskExample example);

    int updateByPrimaryKeySelective(ScheduleTask record);

    int updateByPrimaryKey(ScheduleTask record);
}