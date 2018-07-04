package com.lambo.dict.controller;

import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;
import com.lambo.dict.service.api.LamboDictService;
import com.lambo.dict.utils.DictCacheUtil;
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
 * LamboDictcontroller
 * Author wangjierj on 2018/7/2.
 */
@Controller
@RequestMapping("/manage/lamboDict")
@Api(value = "LamboDict控制器", description = "LamboDict管理")
public class LamboDictController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LamboDictController.class);

    @Autowired
    private LamboDictService lamboDictService;

    @ApiOperation(value = "LamboDict列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("LamboDict分页数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {
        LamboDictExample lamboDictExample = new LamboDictExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            lamboDictExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            lamboDictExample.or().andIdEqualTo(Integer.parseInt(search));
        }
        List<LamboDict> rows = lamboDictService.selectByExampleForOffsetPage(lamboDictExample, offset, limit);
        int total = lamboDictService.countByExample(lamboDictExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return new BaseResult(BaseResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "LamboDict导出表格")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("LamboDict导出表格")
    public Object listExport(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {
        return ((BaseResult) list(offset, limit, sort, order, search)).data;
    }

    @ApiOperation(value = "根据LamboDict主键查询数据")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        return lamboDictService.selectByPrimaryKey(id);
    }


    @ApiOperation(value = "新增LamboDict数据")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, defaultValue = "", value = "dictName") String dictName,
            @RequestParam(required = true, defaultValue = "", value = "dictType") String dictType,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictKey") String dictKey,
            @RequestParam(required = false, defaultValue = "", value = "dictValue") String dictValue,
            @RequestParam(required = false, defaultValue = "0", value = "orderNum") int orderNum,
            @RequestParam(required = false, defaultValue = "", value = "dictDesc") String dictDesc,
            @RequestParam(required = false, defaultValue = "", value = "dictSql") String dictSql,
            @RequestParam(required = false, defaultValue = "", value = "dictDataSource") String dictDataSource
    ) {

        LamboDict lamboDict = new LamboDict();
        lamboDict.setDictName(dictName);
        lamboDict.setDictType(dictType);
        lamboDict.setDictId(dictId);
        lamboDict.setDictKey(dictKey);
        lamboDict.setDictValue(dictValue);
        lamboDict.setOrderNum(orderNum);
        lamboDict.setDictDesc(dictDesc);
        lamboDict.setDictSql(dictSql);
        lamboDict.setDictDataSource(dictDataSource);
        int count = lamboDictService.insertSelective(lamboDict);
        if (count <= 0) {
            return new BaseResult(BaseResultConstant.FAILED, 0);
        }
        return new BaseResult(BaseResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "更新LamboDict数据")
    @ResponseBody
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Object update(
            @PathVariable("id") int id,
            @RequestParam(required = true, defaultValue = "", value = "dictName") String dictName,
            @RequestParam(required = true, defaultValue = "", value = "dictType") String dictType,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictKey") String dictKey,
            @RequestParam(required = false, defaultValue = "", value = "dictValue") String dictValue,
            @RequestParam(required = false, defaultValue = "0", value = "orderNum") int orderNum,
            @RequestParam(required = false, defaultValue = "", value = "dictDesc") String dictDesc,
            @RequestParam(required = false, defaultValue = "", value = "dictSql") String dictSql,
            @RequestParam(required = false, defaultValue = "", value = "dictDataSource") String dictDataSource
    ) {

        LamboDict lamboDict = new LamboDict();
        lamboDict.setId(id);
        if (!StringUtils.isBlank(dictName)) {
            lamboDict.setDictName(dictName);
        }
        if (!StringUtils.isBlank(dictType)) {
            lamboDict.setDictType(dictType);
        }
        if (!StringUtils.isBlank(dictId)) {
            lamboDict.setDictId(dictId);
        }
        if (!StringUtils.isBlank(dictKey)) {
            lamboDict.setDictKey(dictKey);
        }
        if (!StringUtils.isBlank(dictValue)) {
            lamboDict.setDictValue(dictValue);
        }
        lamboDict.setOrderNum(orderNum);
        if (!StringUtils.isBlank(dictDesc)) {
            lamboDict.setDictDesc(dictDesc);
        }
        if (!StringUtils.isBlank(dictSql)) {
            lamboDict.setDictSql(dictSql);
        }
        if (!StringUtils.isBlank(dictDataSource)) {
            lamboDict.setDictDataSource(dictDataSource);
        }
        int count = lamboDictService.updateByPrimaryKeySelective(lamboDict);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除LamboDict数据")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("删除LamboDict数据")
    public Object delete(@PathVariable("ids") String ids) {
        int count = lamboDictService.deleteByPrimaryKeys(ids);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }
}