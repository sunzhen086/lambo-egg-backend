package com.lambo.dict.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;

import java.util.List;
import java.util.Map;

/**
* LamboDictService接口
* Author zxc on 2018/7/2.
*/
public interface LamboDictService extends BaseService<LamboDict, LamboDictExample> {
    public List<Map<String, Object>> getDict(String dictId);
    public int deleteByDictId(String dictId);
    public Object createDict(String dictName,String dictId,String dictDesc,String dictKeyList);
    public Object updateDict(String dictName,String dictId,String dictDesc,String dictKeyLis);
}