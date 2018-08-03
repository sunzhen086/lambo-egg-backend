package com.lambo.mock.client.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.mock.manage.model.MockSetting;
import com.lambo.mock.manage.model.MockSettingExample;
import com.lambo.mock.manage.model.MockSettingParams;
import com.lambo.mock.manage.model.MockSettingParamsExample;
import com.lambo.mock.manage.service.api.MockSettingParamsService;
import com.lambo.mock.manage.service.api.MockSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName MockClientController
 * @Descirption TODO
 * @Author cuiyh
 * @Date 2018/8/3
 **/
@Controller
@Api(value = "mock服务测试", description = "mock服务测试")
public class MockClientController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MockClientController.class);

    private static final String URL_PREFIX = "/mock/service";

    @Autowired
    MockSettingService mockSettingService;

    @Autowired
    MockSettingParamsService mockSettingParamsService;

    @ApiOperation(value = "通用数据服务")
    @RequestMapping(value = URL_PREFIX+"/**",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object query(HttpServletRequest request) {

        MockSetting mockSetting = getMockSettingByUrl(request.getRequestURI());


        String mockId = mockSetting.getMockId();

        StringBuffer msg = new StringBuffer();

        List<MockSettingParams> restSettingParamList = getMockSettingParamByRestId(mockId);

        if(null!=restSettingParamList && restSettingParamList.size()>0){
            String groupKey = restSettingParamList.get(0).getGroupKey();
            if(null==groupKey || "".equals(groupKey)) {
                for(MockSettingParams mockSettingParams : restSettingParamList){
                    String paramKey = mockSettingParams.getParamKey();

                    boolean necessary = mockSettingParams.getNecessary().equals("1");
                    if(necessary){
                        String paramValue = request.getParameter(paramKey);
                        if(null==paramValue || StringUtils.isBlank(paramValue)){
                            msg.append("参数 ").append(paramKey).append(" 不允许为空; ");
                            logger.info("参数 "+paramKey+" 不允许为空");
                        }
                    }
                }
            }else{
                //数组参数校验
                String groupValue = request.getParameter(groupKey);
            }
        }

        logger.info("msg="+msg.toString());

        if(msg.length() > 0){
            return new BaseResult(BaseResultConstant.FAILED,msg.toString());
        }else{
            return mockSetting.getMockData();
        }

    }

    @ApiOperation(value = "获取服务配置")
    @RequestMapping(value = "/client/mock/queryById",method = {RequestMethod.GET})
    @ResponseBody
    public Object queryById(@RequestParam(required = false, value = "mockId") String mockId) {

        return new BaseResult(BaseResultConstant.SUCCESS,mockSettingService.query(mockId));

    }

    @ApiOperation(value = "获取服务配置")
    @RequestMapping(value = "/client/mock/queryByUrl",method = {RequestMethod.GET})
    @ResponseBody
    public Object queryByUrl(@RequestParam(required = false, value = "mockUrl") String mockUrl) {

        MockSetting mockSetting = getMockSettingByUrl(mockUrl);

        List<MockSettingParams> mockSettingParamsList = getMockSettingParamByRestId(mockSetting.getMockId());

        Map map = new HashMap();
        map.put("mockSetting", mockSetting);
        map.put("mockSettingParamsList", mockSettingParamsList);

        return new BaseResult(BaseResultConstant.SUCCESS,map);

    }

    /**
     * 通过url获取服务设置
     * @param mockUrl
     * @return
     */
    private MockSetting getMockSettingByUrl(String mockUrl){
        if(mockUrl == null){
            throw new RuntimeException("服务url不能为空");
        }

        mockUrl = mockUrl.replaceAll(".*" + URL_PREFIX,"");

        MockSettingExample mockSettingExample = new MockSettingExample();
        mockSettingExample.createCriteria().andMockUrlEqualTo(mockUrl.trim());
        List<MockSetting> list = null;
        try{
            list = mockSettingService.selectByExampleWithBLOBs(mockSettingExample);
        }catch (Exception e){
            logger.error("通过url获取服务设置出错",e);
        }

        if(list == null || list.size() == 0){
            throw new RuntimeException("服务"+mockUrl+"不存在,请检查数据");
        }
        if(list.size() > 1){
            throw new RuntimeException("服务"+mockUrl+"重复,请检查数据");
        }
        return list.get(0);
    }

    /**
     * 根据mockId获取服务参数
     * @param mockId
     * @return
     */
    private List<MockSettingParams> getMockSettingParamByRestId(String mockId){

        MockSettingParamsExample mockSettingParamsExample = new MockSettingParamsExample();
        mockSettingParamsExample.createCriteria().andMockIdEqualTo(mockId);
        mockSettingParamsExample.setOrderByClause("ORDER_SEQ ASC");

        List<MockSettingParams> list = null;
        try{
            list = mockSettingParamsService.selectByExample(mockSettingParamsExample);
        }catch (Exception e){
            logger.error("通过id获取服务设置出错",e);
        }finally {

        }
        return list == null ? new ArrayList<MockSettingParams>() : list;
    }
}
