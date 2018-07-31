package com.lambo.mock.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.mock.manage.model.MockSetting;
import com.lambo.mock.manage.model.MockSettingExample;
import com.lambo.mock.manage.model.MockSettingParams;

import java.util.List;

/**
 * MockSettingService
 */
public interface MockSettingService extends BaseService<MockSetting, MockSettingExample> {

    public Integer insert(MockSetting mockSetting,List<MockSettingParams> paramsList);

    public Integer update(MockSetting mockSetting,List<MockSettingParams> paramsList);

    public Object query(String mockId);

}
