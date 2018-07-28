package com.lambo.rule.rpc.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.rule.rpc.api.RuleUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RuleController
 * @Descirption TODO
 * @Author zxc
 * @Date 2018/7/20 16:39
 **/
@Controller
public class RuleController extends BaseController {


    @ApiOperation(value = "获取业务规则返回List")
    @RequestMapping(value = "/rule/getList",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC业务规则List")
    public Object getList(
            @RequestParam(value = "ruleId") String ruleId) {

        List<Map<String,String>> result = RuleUtil.getRuleList(ruleId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取业务规则值")
    @RequestMapping(value = "/rule/getValue",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("获取业务规则值")
    public Object getValue(
            @RequestParam(value = "ruleId") String ruleId,
            @RequestParam(value = "comId") String comId) {

         String result = RuleUtil.getRuleValue(ruleId,comId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }

}
