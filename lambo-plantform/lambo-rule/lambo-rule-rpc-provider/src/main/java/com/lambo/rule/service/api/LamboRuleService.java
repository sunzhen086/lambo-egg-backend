package com.lambo.rule.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.rule.dao.model.LamboRule;
import com.lambo.rule.dao.model.LamboRuleExample;

import java.util.List;
import java.util.Map;

/**
* LamboRuleService接口
* Author zxc on 2018/7/23.
*/
public interface LamboRuleService extends BaseService<LamboRule, LamboRuleExample> {
    public List<Map<String, Object>> getRule(String ruleId);
    public int deleteByRuleId(String ruleId);
    public Object createRule(String ruleId, String dictDesc, String dictKeyList);
    public Object updateRule(String ruleId, String dictDesc, String dictKeyLis);
}