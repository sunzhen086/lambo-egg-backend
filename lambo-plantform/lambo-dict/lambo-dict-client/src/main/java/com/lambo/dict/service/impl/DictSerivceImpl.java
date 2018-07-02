package com.lambo.dict.service.impl;


import com.lambo.dict.dao.api.DictMapper;
import com.lambo.dict.service.api.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * dict工具类
 * @author zxc
 */

@Service("getDictCacheService")
public class DictSerivceImpl implements DictService {
    public static final String DICT_CACHE_TYPE="1";
    @Autowired
    DictMapper dictMapper;
    /**
     * 获取数据字典
     * @param
     * @return
     */
    @Cacheable(value="CacheForEver", key="#dictId")
    @Override
    public  Map getDictData(String dictId){
        Map data =new HashMap();
        List<Map<String,Object>> dataList=null;
         dataList=dictMapper.getDict(dictId);
        if(dataList!=null && dataList.size()>0){
            Map<String,Object> dataMap=dataList.get(0);
            String dataType=(String)dataMap.get("dictType");
            String dataSql=(String)dataMap.get("dictSql");
            //自定义sql
            if(DICT_CACHE_TYPE.equals(dataType)){
                dataList=dictMapper.getSqlDict(dataSql);
            }
            for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
                Map object = (Map) iterator.next();
                String key=(String)object.get("K");
                String value=(String)object.get("V");
                data.put(key,value);
            }
        }
        return data;
    }


}
