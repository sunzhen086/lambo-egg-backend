package com.lambo.mock.manage.service.impl;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.mock.manage.dao.api.MockSettingMapper;
import com.lambo.mock.manage.dao.api.MockSettingParamsMapper;
import com.lambo.mock.manage.model.MockSetting;
import com.lambo.mock.manage.model.MockSettingExample;
import com.lambo.mock.manage.model.MockSettingParams;
import com.lambo.mock.manage.model.MockSettingParamsExample;
import com.lambo.mock.manage.service.api.MockSettingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MockSettingService实现
 *
 */
@Service
@BaseService
public class MockSettingServiceImpl extends BaseServiceImpl<MockSettingMapper, MockSetting, MockSettingExample> implements MockSettingService {

    private static Logger logger = LoggerFactory.getLogger(MockSettingServiceImpl.class);

    @Autowired
    MockSettingMapper mockSettingMapper;

    @Autowired
    MockSettingParamsMapper mockSettingParamsMapper;


    @Override
    @Transactional
    public Integer insert(MockSetting mockSetting, List<MockSettingParams> paramsList){

        //MOCK_SETTING
        int count = mockSettingMapper.insert(mockSetting);

        //MOCK_SETTING_PARAMS
        if(null!=paramsList && paramsList.size()>0){
            for(int i=0;i<paramsList.size();i++){
                mockSettingParamsMapper.insert((MockSettingParams)paramsList.get(i));
            }
        }

        //记录日志

        return count;
    }

    @Override
    @Transactional
    public Integer update(MockSetting mockSetting,List<MockSettingParams> paramsList){

        int count =mockSettingMapper.updateByPrimaryKeyWithBLOBs(mockSetting);

        //MOCK_SETTING_PARAMS
        //先删除后插入
        MockSettingParamsExample mockSettingParamsExample =  new MockSettingParamsExample();
        mockSettingParamsExample.createCriteria().andMockIdEqualTo(mockSetting.getMockId());
        mockSettingParamsMapper.deleteByExample(mockSettingParamsExample);

        if(null!=paramsList && paramsList.size()>0){
            for(int i=0;i<paramsList.size();i++){
                mockSettingParamsMapper.insert((MockSettingParams)paramsList.get(i));
            }
        }

        //记录日志


        return count;
    }

    @Override
    public Object query(String mockId){
        Map dataMap = new HashMap();

        if(null!=mockId || !"".endsWith(mockId)) {

            //MOCK_SETTING
            MockSetting mockSetting = mockSettingMapper.selectByPrimaryKey(mockId);
            if (null != mockSetting) {
                dataMap.put("mockSetting", mockSetting);
            }

            //MOCK_SETTING_PARAMS
            MockSettingParamsExample mockSettingParamsExample = new MockSettingParamsExample();
            mockSettingParamsExample.createCriteria().andMockIdEqualTo(mockId);
            mockSettingParamsExample.setOrderByClause("ORDER_SEQ ASC");

            List<MockSettingParams> mockSettingParamsList = mockSettingParamsMapper.selectByExample(mockSettingParamsExample);
            if (null != mockSettingParamsList) {
                dataMap.put("mockSettingParamsList", mockSettingParamsList);
            }
        }

        return dataMap;
    }

}
