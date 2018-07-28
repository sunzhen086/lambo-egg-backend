package com.lambo.dict.rpc.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.dict.rpc.api.DictUtil;


import com.lambo.rule.utils.RuleUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DictController
 * @Descirption TODO
 * @Author zxc
 * @Date 2018/7/20 16:39
 **/
@Controller
public class DictController extends BaseController {

    @ApiOperation(value = "获取数据字典返回List")
    @RequestMapping(value = "/dict/getList",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC数据字典List")
    public Object getList(
            @RequestParam(value = "dictId") String dictId) {
        List<Map<String,String>> result = DictUtil.getDictList(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取业务规则返回List")
    @RequestMapping(value = "/rule/getList",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC业务规则List")
    public Object getRuleList(
            @RequestParam(value = "ruleId") String ruleId) {
        List<Map<String,String>> result = RuleUtil.getRuleList(ruleId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取业务规则返回Json")
    @RequestMapping(value = "/rule/getJson",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC业务规则List")
    public Object getRuleJson(
            @RequestParam(value = "ruleId") String ruleId) {
        String result = RuleUtil.getRuleJson(ruleId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取业务规则值")
    @RequestMapping(value = "/rule/getValue",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("获取业务规则值")
    public Object getRuleValue(
            @RequestParam(value = "ruleId") String ruleId,
            @RequestParam(value = "comId") String comId) {

        String result = RuleUtil.getRuleValue(ruleId,comId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取数据字典返回Map")
    @RequestMapping(value = "/dict/getMap",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC数据字典Map")
    public Object getMap(
            @RequestParam(value = "dictId") String dictId) {

        LinkedHashMap<String, String> map = DictUtil.getDictMap(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS,map);
    }
    @ApiOperation(value = "获取数据字典返回Json")
    @RequestMapping(value = "/dict/getJson",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC数据字典Json")
    public Object getJson(
            @RequestParam(value = "dictId") String dictId) {

        String  result = DictUtil.getDictJson(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取数据字典值")
    @RequestMapping(value = "/dict/getValue",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("获取数据字典值")
    public Object getValue(
            @RequestParam(value = "dictId") String dictId,
            @RequestParam(value = "key") String key) {

         String result = DictUtil.getDictValue(dictId,key);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }

}
