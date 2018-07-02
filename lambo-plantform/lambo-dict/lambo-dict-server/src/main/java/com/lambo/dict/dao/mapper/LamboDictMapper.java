package com.lambo.dict.dao.mapper;

import com.lambo.dict.dao.model.LamboDict;
import com.lambo.dict.dao.model.LamboDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LamboDictMapper {
    long countByExample(LamboDictExample example);

    int deleteByExample(LamboDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LamboDict record);

    int insertSelective(LamboDict record);

    List<LamboDict> selectByExample(LamboDictExample example);

    LamboDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LamboDict record, @Param("example") LamboDictExample example);

    int updateByExample(@Param("record") LamboDict record, @Param("example") LamboDictExample example);

    int updateByPrimaryKeySelective(LamboDict record);

    int updateByPrimaryKey(LamboDict record);
}