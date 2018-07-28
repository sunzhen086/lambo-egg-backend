package com.inspur.cigar.controller;

import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.lang.StringUtils;
import com.inspur.cigar.dao.model.RestSetting;
import com.inspur.cigar.dao.model.RestSettingExample;
import com.inspur.cigar.service.api.RestSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestSettingcontroller
 * Author sun_zhen on 2018/7/27.
 */
@Controller
@RequestMapping("/manage/restSetting")
@Api(value = "RestSetting控制器", description = "RestSetting管理")
public class RestSettingController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RestSettingController.class);

    @Autowired
    private RestSettingService restSettingService;

    @ApiOperation(value = "RestSetting列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RestSetting分页数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {

        RestSettingExample restSettingExample = new RestSettingExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            restSettingExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            restSettingExample.or().andRestIdEqualTo(search);
        }
        List<RestSetting> rows = restSettingService.selectByExampleForOffsetPage(restSettingExample, offset, limit);
        int total = restSettingService.countByExample(restSettingExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return new BaseResult(BaseResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "RestSetting导出表格")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("RestSetting导出表格")
    public Object listExport(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {
        return ((BaseResult) list(offset, limit, sort, order, search)).data;
    }

    @ApiOperation(value = "根据RestSetting主键查询数据")
    @RequestMapping(value = "/get/{restId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("restId") String restId) {
        RestSettingExample restSettingExample = new RestSettingExample();
        restSettingExample.or().andRestIdEqualTo(restId);
        return restSettingService.selectFirstByExample(restSettingExample);
    }


    @ApiOperation(value = "新增RestSetting数据")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, defaultValue = "", value = "restId") String restId,
            @RequestParam(required = false, defaultValue = "", value = "restName") String restName,
            @RequestParam(required = true, defaultValue = "", value = "url") String url,
            @RequestParam(required = true, defaultValue = "", value = "operationType") String operationType,
            @RequestParam(required = true, defaultValue = "", value = "datasource") String datasource,
            @RequestParam(required = false, defaultValue = "", value = "restSql") String restSql,
            @RequestParam(required = false, defaultValue = "", value = "mockData") String mockData,
            @RequestParam(required = false, defaultValue = "", value = "note") String note,
            @RequestParam(required = true, defaultValue = "", value = "createTime") String createTime,
            @RequestParam(required = true, defaultValue = "", value = "updateTime") String updateTime,
            @RequestParam(required = true, defaultValue = "", value = "createUser") String createUser
    ) {

        RestSetting restSetting = new RestSetting();
        restSetting.setRestId(restId);
        restSetting.setRestName(restName);
        restSetting.setUrl(url);
        restSetting.setOperationType(operationType);
        restSetting.setDatasource(datasource);
        restSetting.setRestSql(restSql);
        restSetting.setMockData(mockData);
        restSetting.setNote(note);
        restSetting.setCreateTime(createTime);
        restSetting.setUpdateTime(updateTime);
        restSetting.setCreateUser(createUser);
        int count = restSettingService.insertSelective(restSetting);
        if (count <= 0) {
            return new BaseResult(BaseResultConstant.FAILED, 0);
        }
        return new BaseResult(BaseResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "更新RestSetting数据")
    @ResponseBody
    @RequestMapping(value = "/update/{restId}", method = RequestMethod.POST)
    public Object update(
            @PathVariable("restId") String restId,
            @RequestParam(required = false, defaultValue = "", value = "restName") String restName,
            @RequestParam(required = true, defaultValue = "", value = "url") String url,
            @RequestParam(required = true, defaultValue = "", value = "operationType") String operationType,
            @RequestParam(required = true, defaultValue = "", value = "datasource") String datasource,
            @RequestParam(required = false, defaultValue = "", value = "restSql") String restSql,
            @RequestParam(required = false, defaultValue = "", value = "mockData") String mockData,
            @RequestParam(required = false, defaultValue = "", value = "note") String note,
            @RequestParam(required = true, defaultValue = "", value = "createTime") String createTime,
            @RequestParam(required = true, defaultValue = "", value = "updateTime") String updateTime,
            @RequestParam(required = true, defaultValue = "", value = "createUser") String createUser
    ) {

        RestSetting restSetting = new RestSetting();
        restSetting.setRestId(restId);
        if (!StringUtils.isBlank(restName)) {
            restSetting.setRestName(restName);
        }
        if (!StringUtils.isBlank(url)) {
            restSetting.setUrl(url);
        }
        if (!StringUtils.isBlank(operationType)) {
            restSetting.setOperationType(operationType);
        }
        if (!StringUtils.isBlank(datasource)) {
            restSetting.setDatasource(datasource);
        }
        if (!StringUtils.isBlank(restSql)) {
            restSetting.setRestSql(restSql);
        }
        if (!StringUtils.isBlank(mockData)) {
            restSetting.setMockData(mockData);
        }
        if (!StringUtils.isBlank(note)) {
            restSetting.setNote(note);
        }
        if (!StringUtils.isBlank(createTime)) {
            restSetting.setCreateTime(createTime);
        }
        if (!StringUtils.isBlank(updateTime)) {
            restSetting.setUpdateTime(updateTime);
        }
        if (!StringUtils.isBlank(createUser)) {
            restSetting.setCreateUser(createUser);
        }
        int count = restSettingService.updateByPrimaryKeySelective(restSetting);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除RestSetting数据")
    @RequestMapping(value = "/delete/{restIds}", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("删除RestSetting数据")
    public Object delete(@PathVariable("restIds") String restIds) {
        int count = 0;
        if (StringUtils.isNotBlank(restIds)) {
            String[] ids = null;
            if (restIds.indexOf(",") > 0) {
                ids = restIds.split(",");
            } else {
                ids = new String[]{restIds};
            }
            for (int i = 0; i < ids.length; i++) {
                RestSettingExample restSettingExample = new RestSettingExample();
                restSettingExample.or().andRestIdEqualTo(ids[i]);
                count += restSettingService.deleteByExample(restSettingExample);
            }
        }
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }
}