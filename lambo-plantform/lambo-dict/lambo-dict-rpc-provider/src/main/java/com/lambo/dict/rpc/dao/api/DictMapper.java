package com.lambo.dict.rpc.dao.api;

import java.util.List;
import java.util.Map;

public interface DictMapper {

    public List<Map<String,String>> getDict(String key);
    public List<Map<String,String>> getSqlDict(String sql);
}