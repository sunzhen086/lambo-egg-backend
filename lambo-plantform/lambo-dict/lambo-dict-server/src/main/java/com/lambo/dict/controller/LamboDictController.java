package com.lambo.dict.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;
import com.lambo.dict.service.api.LamboDictService;
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
 * Author zxc on 2018/7/2.
 */
@Controller
@RequestMapping("/manage/lamboDict")
@Api(value = "LamboDict控制器", description = "LamboDict管理")
public class LamboDictController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LamboDictController.class);
    public static final String DICT_TYPE="0";
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
        if(StringUtils.isBlank(sort)){
            sort = "dictId";
        }
        if(StringUtils.isBlank(order)){
            order = "desc";
        }
        lamboDictExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);
        if (StringUtils.isNotBlank(search)) {
            lamboDictExample.or()
                    .andDictNameLike("%" + search + "%");
        }
        PageHelper.offsetPage(offset, limit);
        List<LamboDict> data = lamboDictService.selectByExample(lamboDictExample);
        PageInfo page = new PageInfo(data);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
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

    @ApiOperation(value = "根据LamboDictId查询数据")
    @RequestMapping(value = "/getDict/{dictId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getDict(@PathVariable("dictId") String dictId) {
        return lamboDictService.getDict(dictId);
    }

    @ApiOperation(value = "新增LamboDict数据")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, defaultValue = "", value = "dictName") String dictName,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictDesc") String dictDesc,
            @RequestParam(required = false, defaultValue = "", value = "dictKeyList") String dictKeyList

    ) {
       return lamboDictService.createDict(dictName,dictId,dictDesc,dictKeyList);
    }

    @ApiOperation(value = "更新LamboDict数据")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(
            @RequestParam(required = true, defaultValue = "", value = "dictName") String dictName,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictKeyList") String dictKeyList,
            @RequestParam(required = false, defaultValue = "", value = "dictDesc") String dictDesc
    ) {
        return lamboDictService.updateDict(dictName,dictId,dictDesc,dictKeyList);
    }

    @ApiOperation(value = "删除LamboDict数据")
    @RequestMapping(value = "/delete/{dictId}", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("删除LamboDict数据")
    public Object delete(@PathVariable("dictId") String dictId) {
        int count = lamboDictService.deleteByDictId(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }


}