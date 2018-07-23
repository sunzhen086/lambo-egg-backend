package com.lambo.auth.client.dao.api;

import com.lambo.auth.client.dao.model.UpmsStUser;
import com.lambo.auth.client.dao.model.UpmsStUserExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsStUserMapper {
    long countByExample(UpmsStUserExample example);

    int deleteByExample(UpmsStUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UpmsStUser record);

    int insertSelective(UpmsStUser record);

    List<UpmsStUser> selectByExampleWithBLOBs(UpmsStUserExample example);

    List<UpmsStUser> selectByExample(UpmsStUserExample example);

    UpmsStUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UpmsStUser record, @Param("example") UpmsStUserExample example);

    int updateByExampleWithBLOBs(@Param("record") UpmsStUser record, @Param("example") UpmsStUserExample example);

    int updateByExample(@Param("record") UpmsStUser record, @Param("example") UpmsStUserExample example);

    int updateByPrimaryKeySelective(UpmsStUser record);

    int updateByPrimaryKeyWithBLOBs(UpmsStUser record);

    int updateByPrimaryKey(UpmsStUser record);
}