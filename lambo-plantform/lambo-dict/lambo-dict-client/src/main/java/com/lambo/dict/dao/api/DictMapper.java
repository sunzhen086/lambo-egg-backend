package com.lambo.dict.dao.api;

import java.util.List;
import java.util.Map;

public interface DictMapper {

    public List<Map<String,Object>> getDict(String key);
    public List<Map<String,Object>> getSqlDict(String sql);
}