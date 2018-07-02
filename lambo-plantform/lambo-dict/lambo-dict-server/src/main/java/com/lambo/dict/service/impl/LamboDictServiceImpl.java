package com.lambo.dict.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.dict.dao.mapper.LamboDictMapper;
import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;
import com.lambo.dict.service.api.LamboDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* LamboDictService实现
* Author wangjierj on 2018/7/2.
*/
@Service
@BaseService
public class LamboDictServiceImpl extends BaseServiceImpl<LamboDictMapper, LamboDict, LamboDictExample> implements LamboDictService {

    private static Logger logger = LoggerFactory.getLogger(LamboDictServiceImpl.class);

    @Autowired
    LamboDictMapper lamboDictMapper;

}