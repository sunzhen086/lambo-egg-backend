package com.lambo.cache.controller;

import com.lambo.cache.service.api.EhCacheService;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author sunzhen
 * @ClassName CacheManageController
 * @Descirption 缓存管理服务
 * @Date 2018/6/26 9:58
 **/
@Controller
@Api(value = "缓存管理服务", description = "缓存管理服务")
@RequestMapping(value = "/manage/cache", method = RequestMethod.GET)
public class CacheManageController extends BaseController {

    @Autowired
    EhCacheService ehCacheService;

    @ApiOperation(value = "新增缓存记录,只支持值为字符串类型的缓存")
    @RequestMapping(value = "/put", method = RequestMethod.GET)
    @ResponseBody
    public Object put(
            @RequestParam(value = "cacheName") String cacheName,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value) {

        ehCacheService.put(cacheName, key, value);
        return new BaseResult(BaseResultConstant.SUCCESS, "新增缓存成功");
    }

    @ApiOperation(value = "删除缓存记录")
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(
            @RequestParam(value = "cacheName") String cacheName,
            @RequestParam(value = "key") String key) {

        ehCacheService.remove(cacheName, key);
        return new BaseResult(BaseResultConstant.SUCCESS, "删除缓存成功");
    }

    @ApiOperation(value = "删除全部缓存记录")
    @RequestMapping(value = "/removeAll", method = RequestMethod.GET)
    @ResponseBody
    public Object removeAll(
            @RequestParam(value = "cacheName") String cacheName) {

        ehCacheService.removeAll(cacheName);
        return new BaseResult(BaseResultConstant.SUCCESS, "清空缓存成功");
    }

    @ApiOperation(value = "获取缓存记录")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Object get(
            @RequestParam(value = "cacheName") String cacheName,
            @RequestParam(value = "key") String key) {

        Object value = ehCacheService.get(cacheName, key);
        return new BaseResult(BaseResultConstant.SUCCESS, value);
    }
}
