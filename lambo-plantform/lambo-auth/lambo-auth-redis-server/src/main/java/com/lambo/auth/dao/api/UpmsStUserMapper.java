package com.lambo.auth.dao.api;

import com.lambo.auth.dao.model.UpmsStUser;
import com.lambo.auth.dao.model.UpmsStUserExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsStUserMapper {
    long countByExample(UpmsStUserExample example);

    int deleteByExample(UpmsStUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpmsStUser record);

    int insertSelective(UpmsStUser record);

    List<UpmsStUser> selectByExampleWithBLOBs(UpmsStUserExample example);

    List<UpmsStUser> selectByExample(UpmsStUserExample example);

    UpmsStUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpmsStUser record, @Param("example") UpmsStUserExample example);

    int updateByExampleWithBLOBs(@Param("record") UpmsStUser record, @Param("example") UpmsStUserExample example);

    int updateByExample(@Param("record") UpmsStUser record, @Param("example") UpmsStUserExample example);

    int updateByPrimaryKeySelective(UpmsStUser record);

    int updateByPrimaryKeyWithBLOBs(UpmsStUser record);

    int updateByPrimaryKey(UpmsStUser record);
}