package com.lambo.dict.rpc.api;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxc
 * @ClassName DictService
 * @Descirption TODO
 * @Date 2018/6/29 14:17
 **/
public interface DictService {

    /**
     *
     * @Title: getDictDataList
     * @Description: 获取字典List
     * @param:dictId
     * @return  List<Map<String, String>>
     * @throws
     */
      List<Map<String, String>> getDictDataList(String dictId);
    /**
     *
     * @Title: getDictMap
     * @Description: 获取字典map
     * @param:dictId
     * @return LinkedHashMap<String,String>
     * @throws
     */
      LinkedHashMap<String, String> getDictMap(String dictId);
    /**
     *
     * @Title: getDictMapJson
     * @Description: 获取枚举map对应的json格式
     * @param dictId
     * @return String
     * @throws
     */

      String getDictMapJson(String dictId);
    /**
     *
     * @Title: getDictValue
     * @Description: 获取枚举值
     * @param：dictId
     * @param：key
     * @return String
     * @throws
     */
      String getDictValue(String dictId, String key);
}
