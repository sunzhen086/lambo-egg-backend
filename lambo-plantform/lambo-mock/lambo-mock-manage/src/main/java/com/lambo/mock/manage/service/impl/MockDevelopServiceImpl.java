package com.lambo.mock.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.mock.manage.dao.api.MockDevelopMapper;
import com.lambo.mock.manage.model.MockDevelop;
import com.lambo.mock.manage.model.MockDevelopExample;
import com.lambo.mock.manage.service.api.MockDevelopService;
import org.springframework.stereotype.Service;

/**
 * MockDevelopService实现
 *
 */
@Service
@BaseService
public class MockDevelopServiceImpl extends BaseServiceImpl<MockDevelopMapper, MockDevelop, MockDevelopExample> implements MockDevelopService {
}
