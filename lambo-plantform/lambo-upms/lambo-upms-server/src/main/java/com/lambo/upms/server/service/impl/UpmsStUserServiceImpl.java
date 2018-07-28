package com.lambo.upms.server.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.upms.client.dao.model.UpmsStUser;
import com.lambo.upms.client.dao.model.UpmsStUserExample;
import com.lambo.upms.server.dao.api.UpmsStUserMapper;
import com.lambo.upms.server.service.api.UpmsStUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* UpmsStUserService实现
* Author wangjierj on 2018/7/27.
*/
@Service
@BaseService
public class UpmsStUserServiceImpl extends BaseServiceImpl<UpmsStUserMapper, UpmsStUser, UpmsStUserExample> implements UpmsStUserService {

    private static Logger logger = LoggerFactory.getLogger(UpmsStUserServiceImpl.class);

    @Autowired
    UpmsStUserMapper upmsStUserMapper;

}