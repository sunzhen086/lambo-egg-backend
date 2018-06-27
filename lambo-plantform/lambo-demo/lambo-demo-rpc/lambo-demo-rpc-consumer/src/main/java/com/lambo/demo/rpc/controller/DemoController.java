package com.lambo.demo.rpc.controller;

import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.demo.rpc.api.DemoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DemoController
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/6/27 16:39
 **/
@Controller
public class DemoController extends BaseController {

    @Autowired
    DemoService permissionService;

    @ApiOperation(value = "RPC测试")
    @RequestMapping(value = "/permission/get",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("RPC测试")
    public Object get(
            @RequestParam(value = "id") long id) {

        List<String> result = permissionService.getPermissions(id);
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }
}
