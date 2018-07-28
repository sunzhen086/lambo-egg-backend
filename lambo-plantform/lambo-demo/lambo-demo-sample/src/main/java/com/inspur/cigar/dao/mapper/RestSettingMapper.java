package com.inspur.cigar.dao.mapper;

import com.inspur.cigar.dao.model.RestSetting;
import com.inspur.cigar.dao.model.RestSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestSettingMapper {
    long countByExample(RestSettingExample example);

    int deleteByExample(RestSettingExample example);

    int deleteByPrimaryKey(String restId);

    int insert(RestSetting record);

    int insertSelective(RestSetting record);

    List<RestSetting> selectByExampleWithBLOBs(RestSettingExample example);

    List<RestSetting> selectByExample(RestSettingExample example);

    RestSetting selectByPrimaryKey(String restId);

    int updateByExampleSelective(@Param("record") RestSetting record, @Param("example") RestSettingExample example);

    int updateByExampleWithBLOBs(@Param("record") RestSetting record, @Param("example") RestSettingExample example);

    int updateByExample(@Param("record") RestSetting record, @Param("example") RestSettingExample example);

    int updateByPrimaryKeySelective(RestSetting record);

    int updateByPrimaryKeyWithBLOBs(RestSetting record);

    int updateByPrimaryKey(RestSetting record);
}