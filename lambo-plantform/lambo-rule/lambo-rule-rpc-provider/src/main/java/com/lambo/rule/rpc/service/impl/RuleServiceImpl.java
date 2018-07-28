package com.lambo.rule.rpc.service.impl;



import com.lambo.common.utils.collect.JsonUtil;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.rule.dao.mapper.LamboRuleOtherMapper;
import com.lambo.rule.rpc.api.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * dict工具类
 * @author zxc
 */

@Service
public class RuleServiceImpl implements RuleService {
    public static final String LAMBO_RULE_CACHE="lambo_rule_cache";
    @Autowired
    LamboRuleOtherMapper lamboRuleOtherMapper;


    @Override
    public List<Map<String, String>> getRuleDataList(String ruleId) {
        System.out.println("getRuleDataList---begin");
        List<Map<String, String>>  list = RedisUtil.getList(LAMBO_RULE_CACHE + "_" + ruleId);
        if(list !=null && list.size()>0){
            System.out.println("从redis获取业务规则");
            return  list;
        }
        List<Map<String, String>>  listData=lamboRuleOtherMapper.getRuleForRedis(ruleId);
        System.out.println("从数据库中获取业务规则");
        RedisUtil.setList(LAMBO_RULE_CACHE + "_" + ruleId,listData);
        return listData;
    }
    @Override
    public String getRuleJson(String ruleId) {
        List<Map<String, String>>  list = getRuleDataList(ruleId);
        return JsonUtil.list2json(list);
    }

    @Override
    public String getRuleValue(String ruleId, String organId) {
        List<Map<String, String>> list=getRuleDataList(ruleId);

        for(Map map:list){
            String com=(String)map.get("organId");
            if(com!=null && com.equals(organId)){
                return map.get("ruleValue").toString();
            }
        }
        return null;
    }


}
