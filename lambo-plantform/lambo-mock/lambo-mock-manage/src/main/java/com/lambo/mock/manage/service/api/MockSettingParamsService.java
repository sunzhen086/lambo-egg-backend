package com.lambo.mock.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.mock.manage.model.MockSettingParams;
import com.lambo.mock.manage.model.MockSettingParamsExample;
import org.springframework.stereotype.Service;

/**
 * MockSettingParamsService实现
 *
 */
@Service
@com.lambo.common.annotation.BaseService
public interface MockSettingParamsService extends BaseService<MockSettingParams, MockSettingParamsExample> {
}
