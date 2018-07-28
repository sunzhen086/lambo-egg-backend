package com.inspur.cigar.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.inspur.cigar.dao.mapper.RestSettingMapper;
import com.inspur.cigar.dao.model.RestSetting;
import com.inspur.cigar.dao.model.RestSettingExample;
import com.inspur.cigar.service.api.RestSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* RestSettingService实现
* Author sun_zhen on 2018/7/27.
*/
@Service
@BaseService
public class RestSettingServiceImpl extends BaseServiceImpl<RestSettingMapper, RestSetting, RestSettingExample> implements RestSettingService {

    private static Logger logger = LoggerFactory.getLogger(RestSettingServiceImpl.class);

    @Autowired
    RestSettingMapper restSettingMapper;

}