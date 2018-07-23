package com.lambo.rule.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.rule.dao.mapper.LamboRuleMapper;
import com.lambo.rule.dao.mapper.LamboRuleOtherMapper;
import com.lambo.rule.dao.model.LamboRule;
import com.lambo.rule.dao.model.LamboRuleExample;
import com.lambo.rule.service.api.LamboRuleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* LamboRuleService实现
* Author zxc on 2018/7/23.
*/
@Service
@BaseService
public class LamboRuleServiceImpl extends BaseServiceImpl<LamboRuleMapper, LamboRule, LamboRuleExample> implements LamboRuleService {

    private static Logger logger = LoggerFactory.getLogger(LamboRuleServiceImpl.class);
    public static final String LAMBO_RULE_CACHE="lambo_rule_cache";
    @Autowired
    LamboRuleMapper lamboRuleMapper;
    @Autowired
    LamboRuleOtherMapper lamboRuleOtherMapper;

    @Override
    public List<Map<String, Object>> getRule(String ruleId){
        return lamboRuleOtherMapper.getRule(ruleId);
    }
    @Override
    public int deleteByRuleId(String ruleId){
        return lamboRuleOtherMapper.deleteByRuleId(ruleId);
    }
    @Override
    @Transactional
    public Object createRule(String ruleId,String ruleDesc,String ruleKeyList)
    {

        LamboRule lamboRule = new LamboRule();
        lamboRule.setRuleDesc(ruleDesc);
        lamboRule.setRuleId(ruleId);
        int count=0;
        JSONArray json = JSONArray.parseArray(ruleKeyList);

        if(json !=null && json.size()>0){
            for (int i = 0; i < json.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject jobData = json.getJSONObject(i);
                Map<String,Object> parmData = new HashMap();
                lamboRule.setComId((String) jobData.get("comId"));
                lamboRule.setRuleValue((String) jobData.get("ruleValue"));
                count  = lamboRuleMapper.insertSelective(lamboRule);
            }
        }
        if (count <= 0) {
            return new BaseResult(BaseResultConstant.FAILED, 0);
        }
        return new BaseResult(BaseResultConstant.SUCCESS, 1);
    }
    @Override
    @Transactional
    public Object updateRule(String ruleId,String ruleDesc,String ruleKeyList)
    {

        LamboRule lamboRule = new LamboRule();

        if (!StringUtils.isBlank(ruleId)) {
            lamboRule.setRuleId(ruleId);
        }
        if (!StringUtils.isBlank(ruleDesc)) {
            lamboRule.setRuleDesc(ruleDesc);
        }
        int count=0;
        JSONArray json = JSONArray.parseArray(ruleKeyList);
        if(json !=null && json.size()>0){
            count=deleteByRuleId(ruleId);
            for (int i = 0; i < json.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject jobData = json.getJSONObject(i);
                Map<String,Object> parmData = new HashMap();
                lamboRule.setComId((String) jobData.get("comId"));
                lamboRule.setRuleValue((String) jobData.get("ruleValue"));
                count  = lamboRuleMapper.insertSelective(lamboRule);
            }
        }
        List<Map<String,String>>listData=lamboRuleOtherMapper.getRuleForRedis(ruleId);
        RedisUtil.setList(LAMBO_RULE_CACHE + "_" + ruleId,listData);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }
}