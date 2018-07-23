package com.lambo.dict.rpc.service.impl;



import com.lambo.common.utils.collect.JsonUtil;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.dict.dao.mapper.LamboDictOtherMapper;
import com.lambo.dict.rpc.api.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * dict工具类
 * @author zxc
 */

@Service
public class DictServiceImpl implements DictService {
    public static final String LAMBO_DICT_CACHE="lambo_dict_cache";
    @Autowired
    LamboDictOtherMapper lamboDictOtherMapper;
    /**
     * 获取数据字典
     * @param
     * @return
     */
    //  @Cacheable(value="Cache600Seconds", key="'dictKeys'+#dictId")

//    public  List getDictData(String dictId){
//        List<Map<String,Object>> dataList=null;
//         dataList=dictMapper.getDict(dictId);
//        if(dataList!=null && dataList.size()>0){
//            Map<String,Object> dataMap=dataList.get(0);
//            String dataType=(String)dataMap.get("dictType");
//            String dataSql=(String)dataMap.get("dictSql");
//            //自定义sql
//            if(DICT_CACHE_TYPE.equals(dataType)){
//                dataList=dictMapper.getSqlDict(dataSql);
//            }
//        }
//        return dataList;
//    }

    @Override
    public List<Map<String, String>> getDictDataList(String dictId) {
        System.out.println("getDictDataList---begin");
        List<Map<String, String>>  list = RedisUtil.getList(LAMBO_DICT_CACHE + "_" + dictId);
        if(list !=null && list.size()>0){
            System.out.println("从redis获取数据字典");
            return  list;
        }
        List<Map<String, String>>  listData=lamboDictOtherMapper.getDictForRedis(dictId);
        System.out.println("从数据库中获取数据字典");
        RedisUtil.setList(LAMBO_DICT_CACHE + "_" + dictId,listData);
        return listData;
    }

    @Override
    public LinkedHashMap<String, String> getDictMap(String dictId) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        List<Map<String, String>> dictList=getDictDataList(dictId);
        for (Map<String, String> temp : dictList) {
            map.put((String) temp.get("K"), (String) temp.get("V"));
        }
        return map;
    }

    @Override
    public String getDictMapJson(String dictId) {
        LinkedHashMap<String, String> map = getDictMap(dictId);
        return JsonUtil.object2json(map);
    }

    @Override
    public String getDictValue(String dictId, String key) {
        LinkedHashMap<String, String> map = getDictMap(dictId);

        String v = key;
        if (map != null && map.get(key) != null) {
            v = map.get(key);
        }

        return v;
    }


}
