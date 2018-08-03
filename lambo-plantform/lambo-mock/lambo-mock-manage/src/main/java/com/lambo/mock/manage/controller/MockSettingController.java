package com.lambo.mock.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.mock.manage.model.MockSetting;
import com.lambo.mock.manage.model.MockSettingParams;
import com.lambo.mock.manage.service.api.MockSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Api(value = "mock服务管理", description = "mock服务管理")
@RequestMapping("/manage/mock/setting")
public class MockSettingController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MockSettingController.class);

    @Autowired
    MockSettingService mockSettingService;

    @ApiOperation(value = "新增mock服务")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增mock服务")
    public Object insert(@RequestParam(required = true, value = "mockId") String mockId,
                         @RequestParam(required = true, value = "mockName") String mockName,
                         @RequestParam(required = false, value = "mockUrl") String mockUrl,
                         @RequestParam(required = false, value = "mockType") String mockType,
                         @RequestParam(required = false, value = "provider") String provider,
                         @RequestParam(required = false, value = "user") String user,
                         @RequestParam(required = false, value = "authMethod") String authMethod,
                         @RequestParam(required = false, value = "mockData") String mockData,
                         @RequestParam(required = false, value = "paramsDes") String paramsDes,
                         @RequestParam(required = false, value = "note") String note,
                         @RequestParam(required = false, value = "groupKey") String groupKey,
                         @RequestParam(required = false, value = "settingParams") String settingParams) {

        logger.info("settingParams="+settingParams);

        //MOCK_SETTING
        MockSetting mockSetting = new MockSetting();

        mockSetting.setMockId(mockId);
        mockSetting.setMockName(mockName);
        mockSetting.setMockUrl(mockUrl);
        mockSetting.setMockType(mockType);
        mockSetting.setProvider(provider);
        mockSetting.setUser(user);
        mockSetting.setAuthMethod(authMethod);
        mockSetting.setMockData(mockData);
        mockSetting.setParamsDes(paramsDes);
        mockSetting.setNote(note);
        mockSetting.setMockData(mockData);

        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = format0.format(date);

        mockSetting.setCreateTime(dateTime);
        mockSetting.setUpdateTime(dateTime);

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        mockSetting.setCreateUser(username);

        //MOCK_SETTING_PARAMS
        List<MockSettingParams> paramsList = getParamsList(mockId,settingParams,groupKey);

        logger.info("paramsList="+paramsList);

        int count = mockSettingService.insert(mockSetting,paramsList);

        if(count == 0){
            return new BaseResult(BaseResultConstant.FAILED,"insert MOCK_SETTING 失败");
        }else{
            return new BaseResult(BaseResultConstant.SUCCESS,mockId);
        }
    }

    @ApiOperation(value = "更新MOCK服务")
    @RequestMapping(value = "/update/{mockId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新mock服务")
    public Object update(@PathVariable("mockId") String mockId,
                         @RequestParam(required = false, value = "mockName") String mockName,
                         @RequestParam(required = false, value = "mockUrl") String mockUrl,
                         @RequestParam(required = false, value = "mockType") String mockType,
                         @RequestParam(required = false, value = "provider") String provider,
                         @RequestParam(required = false, value = "user") String user,
                         @RequestParam(required = false, value = "authMethod") String authMethod,
                         @RequestParam(required = false, value = "mockData") String mockData,
                         @RequestParam(required = false, value = "paramsDes") String paramsDes,
                         @RequestParam(required = false, value = "note") String note,
                         @RequestParam(required = false, value = "createTime") String createTime,
                         @RequestParam(required = false, value = "groupKey") String groupKey,
                         @RequestParam(required = false, value = "settingParams") String settingParams) {

        //MOCK_SETTING
        MockSetting mockSetting = new MockSetting();

        mockSetting.setMockId(mockId);
        mockSetting.setMockName(mockName);
        mockSetting.setMockUrl(mockUrl);
        mockSetting.setMockType(mockType);
        mockSetting.setProvider(provider);
        mockSetting.setUser(user);
        mockSetting.setAuthMethod(authMethod);
        mockSetting.setMockData(mockData);
        mockSetting.setParamsDes(paramsDes);
        mockSetting.setNote(note);
        mockSetting.setCreateTime(createTime);

        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = format0.format(date);
        mockSetting.setUpdateTime(dateTime);

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        mockSetting.setCreateUser(username);

        //MOCK_SETTING_PARAMS
        List<MockSettingParams> paramsList = getParamsList(mockId,settingParams,groupKey);

        int count = mockSettingService.update(mockSetting,paramsList);

        if(count == 0){
            return new BaseResult(BaseResultConstant.FAILED,"update MOCK_SETTING 失败");
        }else{
            return new BaseResult(BaseResultConstant.SUCCESS,mockId);
        }
    }

    @ApiOperation(value = "查询mock服务")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询mock服务")
    public Object get(
            @RequestParam(required = true, value = "mockId") String mockId) {

        return  new BaseResult(BaseResultConstant.SUCCESS,mockSettingService.query(mockId));
    }

    private List getParamsList(String mockId,String settingParams,String groupKey){
        List paramsList = new ArrayList();

        if(null!=settingParams && !("").equals(settingParams)){
            JSONArray paramsJson = JSONArray.parseArray(settingParams);
            if(paramsJson.size()>0){
                for(int i=0;i<paramsJson.size();i++){
                    JSONObject json = paramsJson.getJSONObject(i);
                    MockSettingParams mockSettingParams = new MockSettingParams();
                    mockSettingParams.setMockId(mockId);
                    mockSettingParams.setParamKey((String)json.get("paramKey"));
                    mockSettingParams.setParamType((String)json.get("paramType"));
                    mockSettingParams.setNecessary((String)json.get("necessary"));
                    mockSettingParams.setNote((String)json.get("note"));
                    mockSettingParams.setGroupKey(groupKey);
                    mockSettingParams.setOrderSeq(i+1);

                    paramsList.add(mockSettingParams);
                }
            }
        }

        return paramsList;
    }
}
