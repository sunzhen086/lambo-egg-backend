package com.lambo.demo.service.impl;

import com.lambo.common.base.BaseConstants;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.demo.service.api.RpcTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RpcTestServiceImpl
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/6/22 9:23
 **/
@Service
public class RpcTestServiceImpl implements RpcTestService {

    private static Logger logger = LoggerFactory.getLogger(RpcTestServiceImpl.class);

    @Override
    @Cacheable(value = "CacheForEver", key = "'demo-test-sayHello-'+ #name")
    public Map sayHello(String name) {
        logger.info("name==============" + name);
        Map map = new HashMap();
        map.put("name",name);
        return map;
    }

    @Override
    @CacheEvict(value = "CacheForEver", key = "'demo-test-sayHello-'+ #name")
    public void updateSometing(String name) {
        logger.info("update==============" + name);
    }
}
