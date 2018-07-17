package com.lambo.dict.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.dict.dao.mapper.LamboDictMapper;
import com.lambo.dict.dao.mapper.LamboDictOtherMapper;
import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;
import com.lambo.dict.service.api.LamboDictService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* LamboDictService实现
* Author zxc on 2018/7/2.
*/
@Service
@BaseService
public class LamboDictServiceImpl extends BaseServiceImpl<LamboDictMapper, LamboDict, LamboDictExample> implements LamboDictService {

    private static Logger logger = LoggerFactory.getLogger(LamboDictServiceImpl.class);
    public static final String DICT_TYPE="0";
    @Autowired
    LamboDictMapper lamboDictMapper;
    @Autowired
    LamboDictOtherMapper lamboDictOtherMapper;

    @Override
    public List<Map<String, Object>> getDict(String dictId){
        return lamboDictOtherMapper.getDict(dictId);
    }
    @Override
    public int deleteByDictId(String dictId){
        return lamboDictOtherMapper.deleteByDictId(dictId);
    }
   @Override
   @Transactional
    public Object createDict(String dictName,String dictType,String dictId,String dictDesc, String dictSql,String dictKeyList,String dictDataSource)
    {

        LamboDict lamboDict = new LamboDict();
        lamboDict.setDictName(dictName);
        lamboDict.setDictType(dictType);
        lamboDict.setDictId(dictId);
        lamboDict.setDictDesc(dictDesc);
        int count=0;
        if(DICT_TYPE.equals(dictType)){
            JSONArray json = JSONArray.parseArray(dictKeyList);

            if(json !=null && json.size()>0){
                for (int i = 0; i < json.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject jobData = json.getJSONObject(i);
                    Map<String,Object> parmData = new HashMap();
                    lamboDict.setDictKey((String) jobData.get("dictKey"));
                    lamboDict.setDictValue((String) jobData.get("dictValue"));
                    lamboDict.setOrderNum((String)jobData.get("orderNum"));
                    count  = lamboDictMapper.insertSelective(lamboDict);

                }
            }
        }else{

            lamboDict.setDictSql(dictSql);
            lamboDict.setDictDataSource(dictDataSource);
            count = lamboDictMapper.insertSelective(lamboDict);
        }


        if (count <= 0) {
            return new BaseResult(BaseResultConstant.FAILED, 0);
        }
        return new BaseResult(BaseResultConstant.SUCCESS, 1);
    }
    @Override
    @Transactional
    public Object updateDict(String dictName,String dictType,String dictId,String dictDesc, String dictSql,String dictKeyList,String dictDataSource)
    {

        LamboDict lamboDict = new LamboDict();
        if (!StringUtils.isBlank(dictName)) {
            lamboDict.setDictName(dictName);
        }
        if (!StringUtils.isBlank(dictType)) {
            lamboDict.setDictType(dictType);
        }
        if (!StringUtils.isBlank(dictId)) {
            lamboDict.setDictId(dictId);
        }
        if (!StringUtils.isBlank(dictSql)) {
            lamboDict.setDictSql(dictSql);
        }
        if (!StringUtils.isBlank(dictDataSource)) {
            lamboDict.setDictDataSource(dictDataSource);
        }
        int count=0;
        if(DICT_TYPE.equals(dictType)){
            JSONArray json = JSONArray.parseArray(dictKeyList);

            if(json !=null && json.size()>0){
                count=deleteByDictId(dictId);
                for (int i = 0; i < json.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject jobData = json.getJSONObject(i);
                    Map<String,Object> parmData = new HashMap();
                    lamboDict.setDictKey((String) jobData.get("dictKey"));
                    lamboDict.setDictValue((String) jobData.get("dictValue"));
                    lamboDict.setOrderNum((String)jobData.get("orderNum"));
                    count  = lamboDictMapper.insertSelective(lamboDict);

                }
            }
        }else{

            lamboDict.setDictSql(dictSql);
            lamboDict.setDictDataSource(dictDataSource);
            count=deleteByDictId(dictId);
            count = lamboDictMapper.insertSelective(lamboDict);
        }
       // int count = lamboDictService.updateByPrimaryKeySelective(lamboDict);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }
}