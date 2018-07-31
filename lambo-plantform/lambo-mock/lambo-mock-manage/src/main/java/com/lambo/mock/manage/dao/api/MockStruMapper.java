package com.lambo.mock.manage.dao.api;

import com.lambo.mock.manage.model.MockStru;
import com.lambo.mock.manage.model.MockStruExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MockStruMapper {
    int deleteByExample(MockStruExample example);

    int deleteByPrimaryKey(String struId);

    int insert(MockStru record);

    int insertSelective(MockStru record);

    List<MockStru> selectByExample(MockStruExample example);

    MockStru selectByPrimaryKey(String struId);

    int updateByExampleSelective(@Param("record") MockStru record, @Param("example") MockStruExample example);

    int updateByExample(@Param("record") MockStru record, @Param("example") MockStruExample example);

    int updateByPrimaryKeySelective(MockStru record);

    int updateByPrimaryKey(MockStru record);
}