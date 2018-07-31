package com.lambo.mock.manage.dao.api;

import com.lambo.mock.manage.model.MockSetting;
import com.lambo.mock.manage.model.MockSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MockSettingMapper {
    int deleteByExample(MockSettingExample example);

    int deleteByPrimaryKey(String mockId);

    int insert(MockSetting record);

    int insertSelective(MockSetting record);

    List<MockSetting> selectByExampleWithBLOBs(MockSettingExample example);

    List<MockSetting> selectByExample(MockSettingExample example);

    MockSetting selectByPrimaryKey(String mockId);

    int updateByExampleSelective(@Param("record") MockSetting record, @Param("example") MockSettingExample example);

    int updateByExampleWithBLOBs(@Param("record") MockSetting record, @Param("example") MockSettingExample example);

    int updateByExample(@Param("record") MockSetting record, @Param("example") MockSettingExample example);

    int updateByPrimaryKeySelective(MockSetting record);

    int updateByPrimaryKeyWithBLOBs(MockSetting record);

    int updateByPrimaryKey(MockSetting record);
}