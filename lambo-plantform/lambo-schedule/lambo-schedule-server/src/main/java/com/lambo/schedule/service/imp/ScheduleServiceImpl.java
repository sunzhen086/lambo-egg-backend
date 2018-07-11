package com.lambo.schedule.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.schedule.dao.api.ScheduleOtherMapper;
import com.lambo.schedule.dao.api.ScheduleTaskMapper;
import com.lambo.schedule.model.ScheduleTask;
import com.lambo.schedule.model.ScheduleTaskExample;
import com.lambo.schedule.service.api.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * CategoryService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@BaseService
public class ScheduleServiceImpl extends BaseServiceImpl<ScheduleTaskMapper, ScheduleTask, ScheduleTaskExample> implements ScheduleService {

    private static Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    ScheduleOtherMapper scheduleOtherMapper;
    @Override
   public int changeStateByPrimaryKey(Map taskMap){
       return scheduleOtherMapper.changeStateByPrimaryKey(taskMap);
   }

   @Override
   public List selectByTaskId(Map<String,Object> taskMap){ return scheduleOtherMapper.selectByTaskId(taskMap);
   }
}