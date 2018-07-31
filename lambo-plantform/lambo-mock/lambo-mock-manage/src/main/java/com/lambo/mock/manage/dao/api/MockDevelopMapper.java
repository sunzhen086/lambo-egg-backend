package com.lambo.mock.manage.dao.api;

import com.lambo.mock.manage.model.MockDevelop;
import com.lambo.mock.manage.model.MockDevelopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MockDevelopMapper {
    int deleteByExample(MockDevelopExample example);

    int deleteByPrimaryKey(String mockId);

    int insert(MockDevelop record);

    int insertSelective(MockDevelop record);

    List<MockDevelop> selectByExampleWithBLOBs(MockDevelopExample example);

    List<MockDevelop> selectByExample(MockDevelopExample example);

    MockDevelop selectByPrimaryKey(String mockId);

    int updateByExampleSelective(@Param("record") MockDevelop record, @Param("example") MockDevelopExample example);

    int updateByExampleWithBLOBs(@Param("record") MockDevelop record, @Param("example") MockDevelopExample example);

    int updateByExample(@Param("record") MockDevelop record, @Param("example") MockDevelopExample example);

    int updateByPrimaryKeySelective(MockDevelop record);

    int updateByPrimaryKeyWithBLOBs(MockDevelop record);

    int updateByPrimaryKey(MockDevelop record);
}