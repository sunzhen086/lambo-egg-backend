package com.lambo.rule.dao.api;

import java.util.List;
import java.util.Map;

public interface RuleMapper {

    public List<Map<String,String>> getRule(String key);
}