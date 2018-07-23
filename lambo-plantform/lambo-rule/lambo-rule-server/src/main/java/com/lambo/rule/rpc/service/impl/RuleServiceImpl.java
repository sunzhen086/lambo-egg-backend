package com.lambo.rule.rpc.service.impl;



import com.lambo.common.utils.collect.JsonUtil;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.rule.dao.mapper.LamboRuleOtherMapper;
import com.lambo.rule.rpc.api.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
            System.out.println("从redis获取数据字典");
            return  list;
        }
        List<Map<String, String>>  listData=lamboRuleOtherMapper.getRuleForRedis(ruleId);
        System.out.println("从数据库中获取数据字典");
        RedisUtil.setList(LAMBO_RULE_CACHE + "_" + ruleId,listData);
        return listData;
    }




    @Override
    public String getRuleValue(String ruleId, String comId) {
        List<Map<String, String>> list=getRuleDataList(ruleId);

        for(Map map:list){
            String com=(String)map.get("comId");
            if(com!=null && com.equals(comId)){
                return map.get("ruleValue").toString();
            }
        }
        return null;
    }


}
