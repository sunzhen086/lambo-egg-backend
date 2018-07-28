package com.lambo.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.demo.service.api.TestDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestDataController
 * @Descirption 示例Controller
 * @Author sunzhen
 * @Date 2018/7/26 21:33
 **/
@Controller
@Api(value = "示例程序", description = "示例程序")
@RequestMapping("/test")
public class TestDataController {

    private static Logger logger = LoggerFactory.getLogger(TestDataController.class);

    @Autowired
    TestDataService testDataService;

    @ApiOperation(value = "查询示例数据")
    @RequestMapping(value = "/data/select", method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询示例数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {

        PageHelper.offsetPage(offset, limit);
        List<Map> data = testDataService.select();
        PageInfo page = new PageInfo(data);

        return new BaseResult(BaseResultConstant.SUCCESS, data);
    }
}
