package com.lambo.schedule.constant;

import com.lambo.common.base.BaseResult;

/**
 * 定时器常量类
 * Created by zxc on 2018/6/26.
 */
public class ScheduleResult extends BaseResult {

    public ScheduleResult(ScheduleResultConstant scheduleResultConstant, Object data) {
        super(scheduleResultConstant.getCode(), scheduleResultConstant.getMessage(), data);
    }

}
