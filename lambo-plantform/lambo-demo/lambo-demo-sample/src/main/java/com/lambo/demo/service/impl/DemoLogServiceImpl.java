package com.lambo.demo.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.demo.dao.mapper.LogDemoMapper;
import com.lambo.demo.dao.model.LogDemo;
import com.lambo.demo.dao.model.LogDemoExample;
import com.lambo.demo.service.api.DemoLogService;
import org.springframework.stereotype.Service;

/**
* UpmsLogService实现
* Created by lambo on 2017/3/20.
*/
@Service
@BaseService
public class DemoLogServiceImpl extends BaseServiceImpl<LogDemoMapper, LogDemo, LogDemoExample> implements DemoLogService {

}