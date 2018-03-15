package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import java.util.List;
import java.util.Map;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
    List<Map<String,Object>> querySubject(Map<String, Object> param);
    public Map<String,Object> getSubject(int subjectId);
    public List<Map<String,Object>> querySubjectColumn(int subjectId);
    int insertSubject(Map<String,Object> param);
}