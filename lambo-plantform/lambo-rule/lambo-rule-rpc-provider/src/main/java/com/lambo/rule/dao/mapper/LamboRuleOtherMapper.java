package com.lambo.rule.dao.mapper;



import java.util.List;
import java.util.Map;

public interface LamboRuleOtherMapper {
    public List<Map<String, Object>> getRule(String ruleId);
    public int deleteByRuleId(String ruleId);
    public List<Map<String,String>> getRuleForRedis(String ruleId);
}