package com.lambo.ndp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.ndp.service.DemoUserService;
import com.lambo.ndp.service.FrontendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * Created by wangjie on 2017/11/14.
 */
@Controller()
@Api(value = "前端展示服务", description = "前端展示服务")
@RequestMapping("/manage")
public class frontendController {
    private static Logger logger = LoggerFactory.getLogger(frontendController.class);

    @Autowired
    private FrontendService frontendService;
    /**
     * 分类列表
     * @return
     */
    @ApiOperation(value = "分类列表")
    @RequestMapping(value = "/dataCategory/getCategoryList",method = RequestMethod.POST)
    @ResponseBody
    @LogAround(value="分类列表")
    public List getCategoryList()  {
        List list = frontendService.getCategoryList(null);
        return list;
    }
    /**
     * 专题列表
     * @return
     */
    @ApiOperation(value = "专题列表")
    @RequestMapping(value = "/dataSubject/getSubjectList",method = RequestMethod.POST)
    @ResponseBody
    @LogAround(value="专题列表")
    public List getSubjectList(
            @ApiParam(name="categoryId", value = "分类ID")
            @RequestParam(required = false, defaultValue = "", value = "categoryId") String categoryId)  {
        Map param = new HashMap();
        if(StringUtils.isNotBlank(categoryId)){
            param.put("category_id",categoryId);
        }
        List list = frontendService.getSubjectList(param);
        return list;
    }
    /**
     * 专题详情
     * @return
     */
    @ApiOperation(value = "专题详情")
    @RequestMapping(value = "/dataSubject/getSubjectInfo",method = RequestMethod.POST)
    @ResponseBody
    @LogAround(value="专题详情")
    public List getSubjectInfo(
            @ApiParam(name="subjectId", value = "专题ID")
            @RequestParam(required = false, defaultValue = "", value = "subjectId") String subjectId)  {
        Map param = new HashMap();
        if(StringUtils.isNotBlank(subjectId)){
            param.put("subject_id",subjectId);
        }else{
            return new ArrayList();
        }
        return frontendService.getSubjectInfo(param);
    }
    /**
     * 查询数据
     * @return
     */
    @ApiOperation(value = "查询数据")
    @RequestMapping(value = "/dataSubject/getTableData",method = RequestMethod.GET)
    @ResponseBody
    @LogAround(value="查询数据")
    public Map getTableData(
            @ApiParam(name="offset", value = "起始行号")
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @ApiParam(name="limit", value = "条数")
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @ApiParam(required = true, name="subjectId", value = "专题ID")
            @RequestParam(required = true, defaultValue = "", value = "subjectId") String subjectId,
            @ApiParam(name="params", value = "参数")
            @RequestParam(required = false, defaultValue = "", value = "params") String params)  {
        Map param = new HashMap();
        param.put("offset",offset);
        param.put("limit",limit);
        if(StringUtils.isNotBlank(sort)){
            param.put("sort",sort);
        }
        if(StringUtils.isNotBlank(order)){
            param.put("order",order);
        }
        if(StringUtils.isNotBlank(subjectId)){
            param.put("subject_id",subjectId);
        }else{
            Map result = new HashMap();
            result.put("rows",new ArrayList());
            result.put("total",0);
            return result;
        }
        if(StringUtils.isNotBlank(params)){
            param.put("params",params);
        }
        Map result = frontendService.getTableData(param);
        return result;
    }

    @ApiOperation(value = "导出数据")
    @RequestMapping(value = "/dataSubject/getTableData",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("导出数据")
    public Map exportList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @ApiParam(required = true, name="subjectId", value = "专题ID")
            @RequestParam(required = true, defaultValue = "", value = "subjectId") String subjectId,
            @ApiParam(name="params", value = "参数")
            @RequestParam(required = false, defaultValue = "", value = "params") String params){

        return getTableData(offset,limit,sort,order,subjectId,params);
    }

    /**
     * 查询纬度
     * @return
     */
    @ApiOperation(value = "查询纬度")
    @RequestMapping(value = "/dataSubject/getDimensionData",method = RequestMethod.GET)
    @ResponseBody
    @LogAround(value="查询纬度")
    public Map getDimensionData(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @ApiParam(name="dimensionId", value = "纬度ID")
            @RequestParam(required = true, defaultValue = "", value = "dimensionId") String dimensionId,
            @ApiParam(name="search", value = "查询关键字")
            @RequestParam(required = false, defaultValue = "", value = "search") String search)  {
        Map param = new HashMap();
        param.put("offset",offset);
        param.put("limit",limit);
        if(StringUtils.isNotBlank(sort)){
            param.put("sort",sort);
        }
        if(StringUtils.isNotBlank(order)){
            param.put("order",order);
        }
        if(StringUtils.isNotBlank(dimensionId)){
            param.put("dimensionId",dimensionId);
        }
        if(StringUtils.isNotBlank(search)){
            param.put("search",search);
        }
        return frontendService.getDimensionData(param);
    }

    /**
     * 导出纬度
     * @return
     */
    @ApiOperation(value = "导出纬度")
    @RequestMapping(value = "/dataSubject/getDimensionData",method = RequestMethod.POST)
    @ResponseBody
    @LogAround(value="导出纬度")
    public Map exportDimensionList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @ApiParam(name="dimensionId", value = "纬度ID")
            @RequestParam(required = true, defaultValue = "", value = "dimensionId") String dimensionId,
            @ApiParam(name="search", value = "查询关键字")
            @RequestParam(required = false, defaultValue = "", value = "search") String search)  {

        return getDimensionData(offset,limit,sort,order,dimensionId,search);
    }
}
