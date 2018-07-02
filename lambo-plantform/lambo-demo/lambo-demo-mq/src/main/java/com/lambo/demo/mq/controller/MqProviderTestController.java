package com.lambo.demo.mq.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.mq.client.MQProducterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunzhen
 * @date 2018-06-29
 */
@Controller
@Api(value = "MQ示例", description = "MQ示例")
@RequestMapping("/demo/mq")
public class MqProviderTestController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MqProviderTestController.class);

    @ApiOperation(value = "发送消息")
    @RequestMapping(value = "/sendMessage",method = RequestMethod.GET)
    @ResponseBody
    @LogAround
    public Object sendMessage(
            @RequestParam(value = "data") String data){

        //当消息丢失时为了方便查找，请尽量使用数据的主键，这里仅为模拟
        String id = IdGenerate.nextId();
        //组织消息
        Message message = MQProducterUtil.geneMessage("TestTag",id,data);
        //发送
        MQProducterUtil.send(message);

        return new BaseResult(BaseResultConstant.SUCCESS,null);
    }
}