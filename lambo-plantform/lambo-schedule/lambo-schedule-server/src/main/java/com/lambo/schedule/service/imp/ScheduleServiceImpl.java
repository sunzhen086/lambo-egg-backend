package com.lambo.schedule.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.schedule.dao.api.ScheduleTaskMapper;
import com.lambo.schedule.model.ScheduleTask;
import com.lambo.schedule.model.ScheduleTaskExample;
import com.lambo.schedule.service.api.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CategoryService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@BaseService
public class ScheduleServiceImpl extends BaseServiceImpl<ScheduleTaskMapper, ScheduleTask, ScheduleTaskExample> implements ScheduleService {

    private static Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

}