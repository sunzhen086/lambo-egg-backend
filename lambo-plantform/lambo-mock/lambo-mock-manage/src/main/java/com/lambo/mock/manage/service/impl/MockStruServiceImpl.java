package com.lambo.mock.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.mock.manage.dao.api.MockDataMapper;
import com.lambo.mock.manage.dao.api.MockStruMapper;
import com.lambo.mock.manage.model.MockStru;
import com.lambo.mock.manage.model.MockStruExample;
import com.lambo.mock.manage.service.api.MockStruService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * MockStruService实现
 *
 */
@Service
@BaseService
public class MockStruServiceImpl extends BaseServiceImpl<MockStruMapper, MockStru, MockStruExample> implements MockStruService {

    private static Logger logger = LoggerFactory.getLogger(MockStruServiceImpl.class);

    @Autowired
    MockDataMapper mockDataMapper;

    @Override
    public String selectDevStatusByMockUrl(String mockUrl){

        StringBuffer devStatus = new StringBuffer();

        if(null!=mockUrl && mockUrl!=""){
            List<Map> list = mockDataMapper.selectDevStatusByMockUrl(mockUrl);
            logger.info("status list="+list);

            if(null!=list && list.size()>0){
                int[] arr = {0,0,0,0,0};
                for(int i=0;i<list.size();i++){
                    Map map = list.get(i);
                    String status = (String)map.get("status");
                    BigDecimal number = (BigDecimal)map.get("number");

                    if(null!=number){
                        Integer num = number.intValue();
                        switch(status){
                            case "New":
                                arr[0] = num;
                                break;
                            case "Coding":
                                arr[1] = num;
                                break;
                            case "Fixed":
                                arr[2] = num;
                                break;
                            case "Closed":
                                arr[3] = num;
                                break;
                            case "Confirm":
                                arr[4] = num;
                                break;
                            default:
                                logger.info("未找到统计类型：status="+status+",number="+number);
                        }
                    }
                }

                devStatus.append(arr[0]).append("-").append(arr[1]).append("-").append(arr[2]).append("-").append(arr[3]).append("-").append(arr[4]);
            }
        }

        return devStatus.toString() ;

    }

    @Override
    public String selectDevStatusByMockId(String mockId){
        String devStatus = "";

        if(null!=mockId && mockId!=""){
            Map map = mockDataMapper.selectDevStatusByMockId(mockId);
            if(null != map){
                devStatus = (String)map.get("status");
            }
        }
       return devStatus;
    }
}
