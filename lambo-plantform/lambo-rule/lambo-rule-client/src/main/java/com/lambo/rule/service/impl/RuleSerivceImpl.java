package com.lambo.rule.service.impl;


import com.lambo.common.utils.io.RedisUtil;
import com.lambo.rule.dao.api.RuleMapper;
import com.lambo.rule.service.api.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * dict工具类
 * @author zxc
 */

@Service("getRuleCacheService")
public class RuleSerivceImpl implements RuleService {
    private static final String LAMBO_RULE_CACHE="lambo_rule_cache";

    @Autowired
    RuleMapper ruleMapper;
    /**
     * 获取数据字典
     * @param
     * @return
     */
  //  @Cacheable(value="Cache600Seconds", key="'dictKeys'+#dictId")
    @Override
    public  List getRuleData(String ruleId){
        System.out.println("getRuleData---begin");
        List<Map<String, String>>  list = RedisUtil.getList(LAMBO_RULE_CACHE + "_" + ruleId);
        if(list !=null && list.size()>0){
            System.out.println("从redis获取数据字典");
            return  list;
        }
        List<Map<String,String>> dataList=null;
         dataList=ruleMapper.getRule(ruleId);
        System.out.println("从数据库中获取数据字典"+dataList);
        RedisUtil.setList(LAMBO_RULE_CACHE + "_" + ruleId,dataList);
//        if(dataList!=null && dataList.size()>0){
//            Map<String,Object> dataMap=dataList.get(0);
//            String dataType=(String)dataMap.get("dictType");
//            String dataSql=(String)dataMap.get("dictSql");
//            //自定义sql
//            if(DICT_CACHE_TYPE.equals(dataType)){
//                dataList=dictMapper.getSqlDict(dataSql);
//            }
//        }
        return dataList;
    }


}
