package com.lambo.mock.manage.dao.api;

import com.lambo.mock.manage.model.MockSettingParams;
import com.lambo.mock.manage.model.MockSettingParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MockSettingParamsMapper {
    int deleteByExample(MockSettingParamsExample example);

    int deleteByPrimaryKey(@Param("mockId") String mockId, @Param("paramKey") String paramKey);

    int insert(MockSettingParams record);

    int insertSelective(MockSettingParams record);

    List<MockSettingParams> selectByExample(MockSettingParamsExample example);

    MockSettingParams selectByPrimaryKey(@Param("mockId") String mockId, @Param("paramKey") String paramKey);

    int updateByExampleSelective(@Param("record") MockSettingParams record, @Param("example") MockSettingParamsExample example);

    int updateByExample(@Param("record") MockSettingParams record, @Param("example") MockSettingParamsExample example);

    int updateByPrimaryKeySelective(MockSettingParams record);

    int updateByPrimaryKey(MockSettingParams record);
}