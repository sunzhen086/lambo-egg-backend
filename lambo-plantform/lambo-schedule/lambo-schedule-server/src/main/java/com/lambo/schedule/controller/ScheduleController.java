package com.lambo.schedule.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;

import com.lambo.common.utils.lang.StringUtils;
import com.lambo.schedule.constant.ScheduleResult;
import com.lambo.schedule.constant.ScheduleResultConstant;
import com.lambo.schedule.model.ScheduleTask;
import com.lambo.schedule.model.ScheduleTaskExample;
import com.lambo.schedule.service.api.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lambo.common.validator.LengthValidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类controller
 * Created by z xc on 2018/3/14.
 */
@Controller
@Api(value = "定时任务", description = "定时任务管理")
@RequestMapping("/manage/schedule")
public class ScheduleController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    public static final String CURRENT_STATE_DISABLE="00";
    public static final String CURRENT_STATE_START="10";


    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value = "定时任务列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "taskName") String taskName) {

        ScheduleTaskExample ScheduleTaskExample = new ScheduleTaskExample();
        if(StringUtils.isBlank(sort)){
            sort = "taskId";
        }
        if(StringUtils.isBlank(order)){
            order = "asc";
        }
        ScheduleTaskExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);
        if (StringUtils.isNotBlank(taskName)) {
            ScheduleTaskExample.or().andTaskNameLike("%" + taskName + "%");
        }
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<ScheduleTask> data = scheduleService.selectByExample(ScheduleTaskExample);
        PageInfo page = new PageInfo(data);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new ScheduleResult(ScheduleResultConstant.SUCCESS,result);

    }
    @ApiOperation(value = "定时任务分类列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object listExport(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "taskName") String taskName) {

        return ((ScheduleResult)list(offset,limit,sort,order,taskName)).data;

    }


    @ApiOperation(value = "新增定时任务")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "taskName") String taskName,
            @RequestParam(required = true, value = "month") String month,
            @RequestParam(required = true, value = "dayOfMonth") String dayOfMonth,
            @RequestParam(required = true, value = "dayOfWeek") String dayOfWeek,
            @RequestParam(required = true, value = "hour") String hour,
            @RequestParam(required = true, value = "minute") String minute,
            @RequestParam(required = true, value = "service") String service,
            @RequestParam(required = true, value = "method") String method,
            @RequestParam(required = false, value = "extrainfo") String extrainfo) {

        ScheduleTask schedule = new ScheduleTask();
        ComplexResult result = FluentValidator.checkAll()
                .on(month, new LengthValidator(1, 12, "月份"))
                .on(dayOfMonth, new LengthValidator(1, 31, "日"))
                .on(hour, new LengthValidator(0, 23, "小时"))
                .on(minute, new LengthValidator(0, 59, "分钟"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new ScheduleResult(ScheduleResultConstant.INVALID_LENGTH, result.getErrors());
        }
        String operation=service+"@"+method;
        schedule.setDayofmonth(dayOfMonth);
        schedule.setCurrentState(CURRENT_STATE_START);
        schedule.setDayofweek(dayOfWeek);
        schedule.setExtrainfo(extrainfo);
        schedule.setHour(hour);
        schedule.setMinute(minute);
        schedule.setMonth(month);
        schedule.setTaskName(taskName);
        schedule.setOperation(operation);
        int count = scheduleService.insertSelective(schedule);
        if(count==1){
            return new ScheduleResult(ScheduleResultConstant.SUCCESS, count);
        }else{
            return new ScheduleResult(ScheduleResultConstant.FAILED, count);
        }
    }


    @ApiOperation(value = "删除定时任务")
    @RequestMapping(value = "/delete/{taskId}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("taskId") String taskId) {
        int count = scheduleService.deleteByPrimaryKeys(taskId);
        return new ScheduleResult(ScheduleResultConstant.SUCCESS, count);
    }


    @ApiOperation(value = "修改定时任务")
    @RequestMapping(value = "/update/{taskId}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(
            @PathVariable("taskId") int taskId,
            @RequestParam(required = true, value = "taskName") String taskName,
            @RequestParam(required = true, value = "month") String month,
            @RequestParam(required = true, value = "dayOfMonth") String dayOfMonth,
            @RequestParam(required = true, value = "dayOfWeek") String dayOfWeek,
            @RequestParam(required = true, value = "hour") String hour,
            @RequestParam(required = true, value = "minute") String minute,
            @RequestParam(required = true, value = "service") String service,
            @RequestParam(required = true, value = "method") String method,
            @RequestParam(required = false, value = "extrainfo") String extrainfo) {

        ScheduleTask schedule = new ScheduleTask();
        ComplexResult result = FluentValidator.checkAll()
                .on(month, new LengthValidator(1, 12, "月份"))
                .on(dayOfMonth, new LengthValidator(1, 31, "日"))
                .on(hour, new LengthValidator(0, 23, "小时"))
                .on(minute, new LengthValidator(0, 59, "分钟"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new ScheduleResult(ScheduleResultConstant.INVALID_LENGTH, result.getErrors());
        }
        String operation=service+"@"+method;
        schedule.setDayofmonth(dayOfMonth);
        schedule.setCurrentState("10");
        schedule.setDayofweek(dayOfWeek);
        schedule.setExtrainfo(extrainfo);
        schedule.setHour(hour);
        schedule.setMinute(minute);
        schedule.setMonth(month);
        schedule.setTaskName(taskName);
        schedule.setOperation(operation);
        schedule.setTaskId(taskId);
        int count = scheduleService.updateByPrimaryKeySelective(schedule);
        return new ScheduleResult(ScheduleResultConstant.SUCCESS, count);
    }


    @ApiOperation(value = "根据ID查询定时任务")
    @RequestMapping(value = "/get/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("taskId") int taskId) {
        ScheduleTask scheduleTask = scheduleService.selectByPrimaryKey(taskId);
        return new ScheduleResult(ScheduleResultConstant.SUCCESS, scheduleTask);
    }
}