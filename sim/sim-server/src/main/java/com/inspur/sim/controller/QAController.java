package com.inspur.sim.controller;

import com.inspur.sim.constant.SimResult;
import com.inspur.sim.constant.SimResultConstant;
import com.inspur.sim.util.QAUtil;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qa.engine.bot.sdk.ask.CloudNotInitializedException;

@Api(value = "问答服务", description = "问答服务")
@Controller
public class QAController extends BaseController {


    @ApiOperation(value = "问答服务")
    @RequestMapping(value = "/ask",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求问答服务")
    public Object ask( @RequestParam(value = "question") String question) {
        String askResponse = "";
        try {
            askResponse = QAUtil.ask(question);
        } catch (CloudNotInitializedException e) {
            e.printStackTrace();
        }
        return new SimResult(SimResultConstant.SUCCESS, askResponse);
    }
}