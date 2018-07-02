package com.lambo.dict.utils;



import com.lambo.common.utils.spring.SpringContextUtil;
import com.lambo.dict.service.api.DictService;
import java.util.Map;

/**
 * DictCacheUtil
 * @author zxc
 */
public class DictCacheUtil {

    public static final String CACHE_DICT_NAME = "CacheForEver";

    public static Map getListCacheValue(String dictId){

            DictService dict=(DictService)SpringContextUtil.getBean("getDictCacheService");
            Map  dictMap=dict.getDictData(dictId);


        return dictMap;
    }
}
