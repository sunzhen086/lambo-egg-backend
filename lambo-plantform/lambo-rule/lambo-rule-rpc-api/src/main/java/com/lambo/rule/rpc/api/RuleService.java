package com.lambo.rule.rpc.api;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxc
 * @ClassName RuleService
 * @Descirption TODO
 * @Date 2018/7/23 14:17
 **/
public interface RuleService {

    /**
     *
     * @Title: getDictDataList
     * @Description: 获取字典List
     * @param:dictId
     * @return  List<Map<String, String>>
     * @throws
     */
      List<Map<String, String>> getRuleDataList(String ruleId);
    /**
     *
     * @Title: getDictMap
     * @Description: 获取字典map
     * @param:dictId
     * @return LinkedHashMap<String,String>
     * @throws

      LinkedHashMap<String, String> getDictMap(String dictId);

     *
     * @Title: getDictMapJson
     * @Description: 获取枚举map对应的json格式
     * @paramdictId
     * @return String
     * @throws


      String getDictMapJson(String dictId);

     *
     * @Title: getDictValue
     * @Description: 获取枚举值
     * @param：dictId
     * @param：key
     * @return String
     * @throws
     */
      String getRuleValue(String ruleId, String comId);
}
