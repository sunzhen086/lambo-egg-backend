package com.lambo.rule.dao.mapper;

import com.lambo.rule.dao.model.LamboRule;
import com.lambo.rule.dao.model.LamboRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LamboRuleMapper {
    long countByExample(LamboRuleExample example);

    int deleteByExample(LamboRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LamboRule record);

    int insertSelective(LamboRule record);

    List<LamboRule> selectByExample(LamboRuleExample example);

    LamboRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LamboRule record, @Param("example") LamboRuleExample example);

    int updateByExample(@Param("record") LamboRule record, @Param("example") LamboRuleExample example);

    int updateByPrimaryKeySelective(LamboRule record);

    int updateByPrimaryKey(LamboRule record);
}