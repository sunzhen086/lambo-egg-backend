package com.lambo.dict.dao.mapper;

import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LamboDictOtherMapper {


       public List<Map<String, Object>> getDict(String dictId);
       public int deleteByDictId(String dictId);
       public List<Map<String,String>> getDictForRedis(String key);
       public List<Map<String,String>> getSqlDict(String sql);
}