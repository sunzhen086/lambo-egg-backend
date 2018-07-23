package com.lambo.dict.rpc.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.dict.rpc.api.DictService;
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

    @Autowired
    DictService dictService;

    @ApiOperation(value = "获取数据字典返回List")
    @RequestMapping(value = "/dict/getList",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC数据字典List")
    public Object getList(
            @RequestParam(value = "dictId") String dictId) {

        List<Map<String,String>> result = dictService.getDictDataList(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取数据字典返回Map")
    @RequestMapping(value = "/dict/getMap",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC数据字典Map")
    public Object getMap(
            @RequestParam(value = "dictId") String dictId) {

        LinkedHashMap<String, String> map = dictService.getDictMap(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS,map);
    }
    @ApiOperation(value = "获取数据字典返回Json")
    @RequestMapping(value = "/dict/getJson",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC数据字典Json")
    public Object getJson(
            @RequestParam(value = "dictId") String dictId) {

        String  result = dictService.getDictMapJson(dictId);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "获取数据字典值")
    @RequestMapping(value = "/dict/getValue",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("获取数据字典值")
    public Object getValue(
            @RequestParam(value = "dictId") String dictId,
            @RequestParam(value = "key") String key) {

         String result = dictService.getDictValue(dictId,key);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }

}
