package com.lambo.mock.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.mock.manage.dao.api.MockStruMapper;
import com.lambo.mock.manage.model.MockStru;
import com.lambo.mock.manage.model.MockStruExample;
import com.lambo.mock.manage.service.api.MockStruService;
import org.springframework.stereotype.Service;

/**
 * MockStruService实现
 *
 */
@Service
@BaseService
public class MockStruServiceImpl extends BaseServiceImpl<MockStruMapper, MockStru, MockStruExample> implements MockStruService {
}
