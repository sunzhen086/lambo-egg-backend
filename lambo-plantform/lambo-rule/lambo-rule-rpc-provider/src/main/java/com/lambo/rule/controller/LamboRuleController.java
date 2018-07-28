package com.lambo.rule.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.rule.dao.model.LamboRule;
import com.lambo.rule.dao.model.LamboRuleExample;
import com.lambo.rule.service.api.LamboRuleService;
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
 * LamboRulecontroller
 * Author zxc on 2018/7/23.
 */
@Controller
@RequestMapping("/manage/lamboRule")
@Api(value = "LamboRule控制器", description = "LamboRule管理")
public class LamboRuleController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LamboRuleController.class);

    @Autowired
    private LamboRuleService lamboRuleService;

    @ApiOperation(value = "LamboRule列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("LamboRule分页数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {

        LamboRuleExample lamboRuleExample = new LamboRuleExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            lamboRuleExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            lamboRuleExample.or().andRuleIdLike(search);
        }
        PageHelper.offsetPage(offset, limit);
        List<LamboRule> data = lamboRuleService.selectByExample(lamboRuleExample);
        PageInfo page = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new BaseResult(BaseResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "LamboRule导出表格")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("LamboRule导出表格")
    public Object listExport(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {
        return ((BaseResult) list(offset, limit, sort, order, search)).data;
    }

    @ApiOperation(value = "根据LamboRuleId查询数据")
    @RequestMapping(value = "/get/{ruleId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("ruleId") String ruleId) {
        LamboRuleExample lamboRuleExample = new LamboRuleExample();
        lamboRuleExample.or().andRuleIdEqualTo(ruleId);
        return lamboRuleService.selectFirstByExample(lamboRuleExample);
    }
    @ApiOperation(value = "根据LamboRuleId查询数据")
    @RequestMapping(value = "/getRule/{ruleId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getRule(@PathVariable("ruleId") String ruleId) {
        return lamboRuleService.getRule(ruleId);
    }

    @ApiOperation(value = "新增LamboRule数据")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(

            @RequestParam(required = true, defaultValue = "", value = "ruleId") String ruleId,
            @RequestParam(required = false, defaultValue = "", value = "ruleKeyList") String ruleKeyList,
            @RequestParam(required = false, defaultValue = "", value = "ruleDesc") String ruleDesc
    ) {
        return lamboRuleService.createRule(ruleId,ruleDesc,ruleKeyList);
    }

    @ApiOperation(value = "更新LamboRule数据")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(
            @RequestParam(required = true, defaultValue = "", value = "ruleId") String ruleId,
            @RequestParam(required = false, defaultValue = "", value = "ruleKeyList") String ruleKeyList,
            @RequestParam(required = false, defaultValue = "", value = "ruleDesc") String ruleDesc
    ) {
        return lamboRuleService.updateRule(ruleId,ruleDesc,ruleKeyList);
    }

    @ApiOperation(value = "删除LamboRule数据")
    @RequestMapping(value = "/delete/{ruleId}", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("删除LamboRule数据")
    public Object delete(@PathVariable("ruleId") String ruleId) {
        int count = 0;
        count += lamboRuleService.deleteByRuleId(ruleId);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }
}