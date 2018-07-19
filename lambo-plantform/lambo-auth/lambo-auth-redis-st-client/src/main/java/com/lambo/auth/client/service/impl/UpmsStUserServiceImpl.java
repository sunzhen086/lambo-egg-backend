package com.lambo.auth.client.service.impl;

import com.lambo.auth.client.dao.api.UpmsStUserMapper;
import com.lambo.auth.client.dao.model.UpmsStUser;
import com.lambo.auth.client.dao.model.UpmsStUserExample;
import com.lambo.auth.client.service.api.UpmsStUserService;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* UpmsStUserService实现
* Author wangjierj on 2018/7/17.
*/
@Service
@BaseService
public class UpmsStUserServiceImpl extends BaseServiceImpl<UpmsStUserMapper, UpmsStUser, UpmsStUserExample> implements UpmsStUserService {

    private static Logger logger = LoggerFactory.getLogger(UpmsStUserServiceImpl.class);

    @Autowired
    UpmsStUserMapper upmsStUserMapper;

}