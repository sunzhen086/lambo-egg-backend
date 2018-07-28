package com.lambo.rule.utils;



import com.lambo.common.utils.collect.JsonUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import com.lambo.rule.service.api.RuleService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DictUtil
 * @author zxc
 */
public class RuleUtil {

    /**
     *
     * @Title: getRuleList
     * @Description: 业务规则List
     * @param:dictId
     * @return  List<Map<String, Object>>
     * @throws
     */
    public static List<Map<String, String>> getRuleList(String ruleId) {
        RuleService rule=(RuleService)SpringContextUtil.getBean("getRuleCacheService");
        List<Map<String, String>> dataList=rule.getRuleData(ruleId);
        return dataList;
    }
    /**
     *
     * @Title: getDictMap
     * @Description: 获取字典map
     * @param:dictId
     * @return LinkedHashMap<String,String>
     * @throws
     */
    public static LinkedHashMap<String, String> getRuleMap(String ruleId) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        List<Map<String, String>> ruleList=getRuleList(ruleId);
        for (Map<String, String> temp : ruleList) {
            map.put((String) temp.get("organId"), (String) temp.get("ruleValue"));
        }
        return map;
    }
    /**
     *
     * @Title: getRuleJson
     * @Description: 获取枚举map对应的json格式
     * @param ruleId
     * @return String
     * @throws
     */

    public static String getRuleJson(String ruleId) {
        List<Map<String, String>> ruleList=getRuleList(ruleId);
        return JsonUtil.object2json(ruleList);
    }
    /**
     *
     * @Title: getRuleValue
     * @Description: 获取枚举值
     * @param：dictId
     * @param：key
     * @return String
     * @throws
     */
    public static String getRuleValue(String ruleId, String organId) {

        LinkedHashMap<String, String> map = getRuleMap(ruleId);

        String v = organId;
        if (map != null && map.get(organId) != null) {
            v = map.get(organId);
        }

        return v;
    }

}
