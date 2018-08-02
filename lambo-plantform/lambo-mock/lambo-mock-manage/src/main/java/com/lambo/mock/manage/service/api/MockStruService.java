package com.lambo.mock.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.mock.manage.model.MockStru;
import com.lambo.mock.manage.model.MockStruExample;

import java.util.List;
import java.util.Map;

/**
 * MockStruService
 */
public interface MockStruService extends BaseService<MockStru, MockStruExample> {

    public String selectDevStatusByMockUrl(String mockUrl);

    public String selectDevStatusByMockId(String mockId);

}
