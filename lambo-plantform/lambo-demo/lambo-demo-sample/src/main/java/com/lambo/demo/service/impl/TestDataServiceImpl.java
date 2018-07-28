package com.lambo.demo.service.impl;

import com.lambo.demo.dao.mapper.TestDataMapper;
import com.lambo.demo.service.api.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestDataService
 * @Descirption 示例Service
 * @Author sunzhen
 * @Date 2018/7/26 21:31
 **/
@Service
public class TestDataServiceImpl implements TestDataService {

    @Autowired
    TestDataMapper testDataMapper;

    @Override
    public List<Map> select(){
        return testDataMapper.select();
    }

}
