package com.lambo.mock.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.mock.manage.dao.api.MockSettingParamsMapper;
import com.lambo.mock.manage.model.MockSettingParams;
import com.lambo.mock.manage.model.MockSettingParamsExample;
import com.lambo.mock.manage.service.api.MockSettingParamsService;
import org.springframework.stereotype.Service;

/**
 * MockSettingParamsService实现
 *
 */
@Service
@BaseService
public class MockSettingParamsServiceImpl extends BaseServiceImpl<MockSettingParamsMapper, MockSettingParams, MockSettingParamsExample> implements MockSettingParamsService {
}
