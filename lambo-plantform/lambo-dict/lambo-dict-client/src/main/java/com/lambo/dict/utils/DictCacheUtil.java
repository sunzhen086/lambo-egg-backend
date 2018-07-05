package com.lambo.dict.utils;



import com.lambo.common.utils.collect.JsonUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import com.lambo.dict.service.api.DictService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DictCacheUtil
 * @author zxc
 */
public class DictCacheUtil {

    /**
     *
     * @Title: getDictDataList
     * @Description: 获取字典List
     * @param:dictId
     * @return  List<Map<String, Object>>
     * @throws
     */
    public static List<Map<String, Object>> getDictDataList(String dictId) {
        DictService dict=(DictService)SpringContextUtil.getBean("getDictCacheService");
        List<Map<String, Object>> dataList=dict.getDictData(dictId);
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
    public static LinkedHashMap<String, String> getDictMap(String dictId) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        List<Map<String, Object>> dictList=getDictDataList(dictId);
        for (Map<String, Object> temp : dictList) {
            map.put((String) temp.get("K"), (String) temp.get("V"));
        }
        return map;
    }
    /**
     *
     * @Title: getDictMapJson
     * @Description: 获取枚举map对应的json格式
     * @param dictId
     * @return String
     * @throws
     */

    public static String getDictMapJson(String dictId) {

        LinkedHashMap<String, String> map = getDictMap(dictId);

        return JsonUtil.object2json(map);
    }
    /**
     *
     * @Title: getDictValue
     * @Description: 获取枚举值
     * @param：dictId
     * @param：key
     * @return String
     * @throws
     */
    public static String getDictValue(String dictId, String key) {

        LinkedHashMap<String, String> map = getDictMap(dictId);

        String v = key;
        if (map != null && map.get(key) != null) {
            v = map.get(key);
        }

        return v;
    }

}
